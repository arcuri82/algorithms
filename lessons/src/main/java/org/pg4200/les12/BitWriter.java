package org.pg4200.les12;

import java.util.ArrayList;
import java.util.List;

/**
 * Programs and CPUs work at byte (8 bits) level.
 * Even a boolean in Java takes actually 8 bits (eg, in
 * an array of booleans).
 * <p>
 * However, for compression, we need to work at bit level, to
 * exploit all the available space.
 * For example, we want to use a single bit for representing
 * a boolean, and not 8.
 * <p>
 * So, in this class, we create a data structure that can be
 * considered like a list of bits, where we can add data
 * in chunks of bits, ie at a finer granularity than
 * working with bytes.
 * <p>
 * But there is no silver bullet: the downside here is that
 * reading/parsing such data at bit level is much more expensive
 * <p>
 * Note: to be precise, the situation is even more complex.
 * The type boolean is the only one whose size is not actually
 * defined, as JVM implementation dependent (although usually 1 byte).
 * Furthermore, the JVM bytecode on the stack works with chunks of 32 bits,
 * so types "byte" and "boolean" actually consumes 32 bits on the instruction
 * execution stack. But that is not the case for when storing arrays in the heap,
 * in which the granularity is at byte level.
 * <p>
 * Created by arcuri82 on 30-Oct-17.
 */
public class BitWriter {

    /**
     * It contains the actual data, in bytes.
     * Note: there is no primitive type for "bit".
     */
    private List<Byte> data;

    /**
     * We add a new byte to the "data" list each time
     * we have written at least 8 bits.
     * Once we write such 8 bits, the buffer gets added
     * into "data" and then reinitialized.
     */
    private byte buffer;

    /**
     * Index on the buffer, counting how many bits it contains,
     * so from 0 to 8.
     * When it reaches 8, we need to flush the buffer into the
     * "data" list, and create a new buffer.
     */
    private int n;

    /**
     * Keep track of whether this reader is closed.
     * Once closed, we cannot add any more bits to it.
     */
    private boolean closed;


    public BitWriter() {
        buffer = 0;
        n = 0;
        data = new ArrayList<>();
        closed = false;
    }

    private void writeBit(boolean bit) {
        checkClosed();

        /*
            shift current data in the buffer to the left.
            Eg, if I have

            xxxx1011

            (where we ignore the context with x) I ll get

            xxx10110

            where rightmost new introduced value is a 0
         */
        buffer <<= 1;

        if (bit) {
            /*
                if true, we need to add a 1.
                We do this by doing an "or" with 1,
                which will just put a 1 bit on rightmost
                position on the 8 bit buffer.

                For example:

                xxx10110
                00000001
                --------
                xxx10111

             */

            buffer |= 1;
        }
        /*
            Note: if "bit" is false, we would need to add
            a 0 on the rightmost position.
            But that is already done when left-shifting
            with <<= 1
         */

        n++;

        if (n == 8) {
            /*
                The byte "buffer" can hold up to 8 bits.
                So it is time to flush it.
             */
            clearBuffer();
        }
    }

    private void clearBuffer() {
        if (n == 0) {
            //nothing in the buffer
            return;
        }

        /*
            if n!=8, we need "padding".
            for example, consider 5 bits

            xxx10100

            and current bytes in "data" list:

            01001110 11101011

            adding the buffer would create a hole of 3 bits xxx, eg

            01001110 11101011 xxx10100

            so, we need to left-shift of 3 bits

            01001110 11101011 10100xxx

            those 3 xxx would actually be 000.
            But how to determine if this 000 are end noise or actual
            data? For decoding, we need to keep track of
            how many bits we write, eg, 8 + 8 + 5 = 23 in this example.
         */
        buffer <<= (8 - n);

        data.add(buffer);

        n = 0;
        buffer = 0;
    }

    public void write(boolean x) {
        writeBit(x);
    }

    public void write(byte b) {
        writeByte(b & 0xFF);
    }

    public void write(int x) {

        /*
            leftmost byte, out of 4. Given

            x = (first 8 bits)(last 24 bits)

            the right-shift 24 leads to the value

            z = (24 bits of 0 data)(first 8 bits)
         */
        writeByte(x >>> 24);

        /*
            Reading the second byte is more tricky.

            x = (first 8 bits)(second byte)(remaining 16 bits)

            After the 16 bit shifting, I get

            z = (16 bits of 0 data)(first 8 bits) (second byte)

            We need to get rid of the "first 8 bits", and
            we do that by a "and" mask with

            0...011111111

            to get only the rightmost 8 bits in z,
            which are the second byte in x, ie

            00...00 kkkkkkkk wwwwwwww
            00...00 00000000 11111111
            -------------------------
            00...00 00000000 wwwwwwww
         */
        writeByte((x >>> 16) & 0xFF); //2nd byte
        writeByte((x >>> 8) & 0xFF); //3rd byte
        writeByte(x & 0xFF); // 4th, rightmost byte
    }

    /**
     * Write the rightmost nbits of the input integer.
     * The info about the leftmost "32 - nbits" is lost.
     */
    public void write(int x, int nbits) {
        if (nbits <= 0 || nbits > 32) {
            throw new IllegalArgumentException("Invalid number of bits: " + nbits);
        }
        if (nbits == 32) {
            //simple case
            write(x);
            return;
        }

        for(int j = nbits-1; j >=0; j--){
            boolean bit = getBitAt(x, j);
            writeBit(bit);
        }
    }

    /**
     * Position is from right to left.
     */
    protected boolean getBitAt(int x, int position) {
        if (position < 0 || position >= 32) {
            throw new IllegalArgumentException("Invalid position: " + position);
        }

        //make sure the ith position is shifted to position 0
        x = x >> position;

        boolean bit  = (x & 1) > 0;

        return bit;
    }

    public void write(String s) {
        for (int i = 0; i < s.length(); i++) {
            write(s.charAt(i));
        }
    }

    public void write(char c) {
        //chars are 2 bytes in Java, as using UTF-16 encoding
        writeByte((c >>> 8) & 0xFF);
        writeByte(c & 0xFF);
    }

    private void writeByte(int x) {
        assert x >= 0 && x < 256;

        if (n == 0) {
            /*
                simple case, the buffer is empty,
                so we can write directly without
                considering it
             */
            data.add((byte) x);
            return;
        }

        /*
            Tricky case. The buffer has already n bits in it,
            and adding a whole new byte will for sure fill the
            buffer, flush it, and then require a new one.
            So, we just write 1 bit at a time, as the flushing
            is already handled inside the writeBit method
         */

        for (int i = 0; i < 8; i++) {
            /*
                The "& 1" is used to get the rightmost bit, as
                the integer value 1 has 000...0001 bit representation
             */
            boolean bit = ((x >>> (8 - i - 1)) & 1) == 1;
            writeBit(bit);
        }
    }

    /**
     * Close the reader, and return its data as an array of bytes
     */
    public byte[] extract() {
        close();
        byte[] result = new byte[data.size()];

        for (int i = 0; i < data.size(); i++) {
            result[i] = data.get(i);
        }

        return result;
    }

    public void close() {
        if (closed) {
            return;
        }
        clearBuffer();
        closed = true;
    }


    private void checkClosed() {
        if (closed) {
            throw new IllegalStateException("Closed");
        }
    }
}
