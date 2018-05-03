package org.pg4200.les12;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by arcuri82 on 30-Oct-17.
 */
public class BitWriterTest {

    @Test
    public void testByte(){

        BitWriter data = new BitWriter();

        byte val = 5;
        data.write(val);

        byte[] res = data.extract();

        assertEquals(1, res.length);
        assertEquals(val, res[0]);
    }

    @Test
    public void testNegativeByte(){

        BitWriter data = new BitWriter();

        byte val = -126;
        data.write(val);

        byte[] res = data.extract();

        assertEquals(1, res.length);
        assertEquals(val, res[0]);
    }

    @Test
    public void testFalse(){

        BitWriter data = new BitWriter();

        data.write(false);

        byte[] res = data.extract();

        assertEquals(1, res.length);
        assertEquals(0, res[0]);
    }

    @Test
    public void testTrue(){

        BitWriter data = new BitWriter();

        data.write(true);

        byte[] res = data.extract();

        assertEquals(1, res.length);
        assertEquals((byte)0b1000_0000, res[0]);
    }

    @Test
    public void testTrueFalseTrue(){

        BitWriter data = new BitWriter();

        data.write(true);
        data.write(false);
        data.write(true);

        byte[] res = data.extract();

        assertEquals(1, res.length);
        assertEquals((byte)0b1010_0000, res[0]);
    }

    @Test
    public void testBooleanAndByte(){

        BitWriter data = new BitWriter();

        data.write(false);
        data.write((byte)3); //00000011


        byte[] res = data.extract();

        assertEquals(2, res.length);
        assertEquals((byte) 1, res[0]);
        assertEquals((byte) -128, res[1]);
    }

    @Test
    public void testSmallInt(){

        BitWriter data = new BitWriter();

        int val = 5;
        data.write(val);

        byte[] res = data.extract();

        assertEquals(4, res.length);
        assertEquals(0, res[0]);
        assertEquals(0, res[1]);
        assertEquals(0, res[2]);
        assertEquals(val, res[3]);
    }

    @Test
    public void testMediumInt(){

        BitWriter data = new BitWriter();

        int val = 0x00_00_01_00;
        data.write(val);

        byte[] res = data.extract();

        assertEquals(4, res.length);
        assertEquals(0, res[0]);
        assertEquals(0, res[1]);
        assertEquals(1, res[2]);
        assertEquals(0, res[3]);
    }

    @Test
    public void testLargeInt(){

        BitWriter data = new BitWriter();

        int val = 0x01_01_01_01;
        data.write(val);

        byte[] res = data.extract();

        assertEquals(4, res.length);
        assertEquals(1, res[0]);
        assertEquals(1, res[1]);
        assertEquals(1, res[2]);
        assertEquals(1, res[3]);
    }


    @Test
    public void testIntAsNegativeByte(){

        BitWriter data = new BitWriter();

        int val = 0b1000_0000;
        data.write(val);

        byte[] res = data.extract();

        assertEquals(4, res.length);
        assertEquals(0, res[0]);
        assertEquals(0, res[1]);
        assertEquals(0, res[2]);
        assertEquals((byte)-128, res[3]);
    }

    @Test
    public void testString(){

        BitWriter data = new BitWriter();

        String abc = "abc";

        data.write(abc);

        byte[] res = data.extract();

        assertEquals(6, res.length);
        assertEquals(0, res[0]);
        assertEquals('a', res[1]);
        assertEquals(0, res[2]);
        assertEquals('b', res[3]);
        assertEquals(0, res[4]);
        assertEquals('c', res[5]);
    }

    @Test
    public void testChar(){

        BitWriter data = new BitWriter();

        char a = 'a';

        data.write(a);

        byte[] res = data.extract();

        assertEquals(2, res.length);
        assertEquals(0, res[0]);
        assertEquals('a', res[1]);
    }

    @Test
    public void testWritePartsOfInt(){

        for(int nbits=1; nbits<=12; nbits++){

            int max = (int) Math.pow(2, nbits);
            BitWriter writer = new BitWriter();

            for(int i=0; i<max; i++){
                writer.write(i, nbits);
            }

            BitReader reader = new BitReader(writer.extract());

            for(int i=0; i<max; i++){
               int x = reader.readInt(nbits);
               assertEquals(i, x);
            }
        }
    }
}