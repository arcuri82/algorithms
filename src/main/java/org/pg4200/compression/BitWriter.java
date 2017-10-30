package org.pg4200.compression;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arcuri82 on 30-Oct-17.
 */
public class BitWriter {

    private byte buffer;

    private int n;

    private List<Byte> data;

    private boolean closed;

    public BitWriter() {
        buffer = 0;
        n = 0;
        data = new ArrayList<>();
        closed = false;
    }

    private void writeBit(boolean bit) {
        checkClosed();

        buffer <<= 1;
        if (bit) {
            buffer |= 1;
        }
        n++;

        if (n == 8) {
            clearBuffer();
        }
    }

    public void write(boolean x) {
        writeBit(x);
    }

    public void write(byte b){
        writeByte(b & 0xFF);
    }

    public void write(int x) {
        writeByte((x >>> 24) & 0xFF);
        writeByte((x >>> 16) & 0xFF);
        writeByte((x >>>  8) & 0xFF);
        writeByte(x  & 0xFF);
    }

    public void write(String s){
        byte[] sb = s.getBytes(Charset.forName("UTF-8"));
        for(byte b : sb){
            write(b);
        }
    }

    private void writeByte(int x){
        assert x >= 0 && x < 256;

        if (n == 0) {
            data.add((byte)x);
            return;
        }

        for (int i = 0; i < 8; i++) {
            boolean bit = ((x >>> (8 - i - 1)) & 1) == 1;
            writeBit(bit);
        }
    }

    public byte[] extract() {
        close();
        byte[] result = new byte[data.size()];

        for(int i=0; i<data.size(); i++){
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

    private void clearBuffer() {
        if (n == 0) {
            return;
        }

        //padding
        buffer <<= (8 - n);

        data.add(buffer);

        n = 0;
        buffer = 0;
    }

    private void checkClosed() {
        if (closed) {
            throw new IllegalStateException("Closed");
        }
    }
}
