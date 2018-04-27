package org.pg4200.les12;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by arcuri82 on 30-Oct-17.
 */
public class BitReaderTest {


    @Test
    public void testByte(){

        byte val = 5;

        BitReader reader = new BitReader(new byte[]{val});

        byte res = reader.readByte();

        assertEquals(val, res);
    }

    @Test
    public void testNegativeByte(){

        byte val = -125;

        BitReader reader = new BitReader(new byte[]{val});

        byte res = reader.readByte();

        assertEquals(val, res);
    }

    @Test
    public void testBoolean(){

        byte val = (byte)0b1000_0000;

        BitReader reader = new BitReader(new byte[]{val});

        boolean b = reader.readBoolean();

        assertTrue(b);
    }

    @Test
    public void testTrueFalseTrue(){

        byte val = (byte)0b1010_0000;

        BitReader reader = new BitReader(new byte[]{val});

        assertTrue(reader.readBoolean());
        assertFalse(reader.readBoolean());
        assertTrue(reader.readBoolean());
    }

    @Test
    public void testTBooleanByteBoolean(){

        byte a = (byte)0b1000_0000;
        byte b = (byte)0b1100_0000;

        BitReader reader = new BitReader(new byte[]{a,b});

        assertTrue(reader.readBoolean());
        assertEquals((byte) 1, reader.readByte());
        assertTrue(reader.readBoolean());
        assertFalse(reader.readBoolean());
    }

    @Test
    public void testWriterAndReader(){

        BitWriter writer = new BitWriter();
        writer.write(true);
        writer.write((byte) 1);
        writer.write(true);

        byte[] data = writer.extract();

        BitReader reader = new BitReader(data);

        assertTrue(reader.readBoolean());
        assertEquals((byte) 1, reader.readByte());
        assertTrue(reader.readBoolean());
        assertFalse(reader.readBoolean());
    }

    @Test
    public void testSmallInt(){

        int val = 5;
        BitWriter writer = new BitWriter();
        writer.write(val);

        BitReader reader = new BitReader(writer.extract());

        int res = reader.readInt();

        assertEquals(val, res);
    }

    @Test
    public void testIntWithNegativeBytes(){

        int val = 0b1000_0000_1000_0000;
        BitWriter writer = new BitWriter();
        writer.write(val);

        BitReader reader = new BitReader(writer.extract());

        int res = reader.readInt();

        assertEquals(val, res);
    }

    @Test
    public void testStringChar(){

        String val = "abc";
        BitWriter writer = new BitWriter();
        writer.write(val);

        BitReader reader = new BitReader(writer.extract());

        char  a = reader.readChar();
        char  b = reader.readChar();
        char  c = reader.readChar();

        assertEquals('a', a);
        assertEquals('b', b);
        assertEquals('c', c);
    }
}