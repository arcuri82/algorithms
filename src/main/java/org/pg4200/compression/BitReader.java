package org.pg4200.compression;

import java.util.Objects;

/**
 * Class used to read byte[] data encoded with BitWriter
 *
 * Created by arcuri82 on 30-Oct-17.
 */
public class BitReader {

    private final byte[] data;

    int bit = 0;

    public BitReader(byte[] data) {
        this.data = Objects.requireNonNull(data);
    }

    public byte readByte(){
        if(bit % 8 == 0){
            int i = bit / 8;
            bit += 8;
            return data[i];
        }

        byte tmp = 0;

        for(int j=0; j<8; j++){
            tmp = (byte) (tmp << 1);

            boolean k  = readBoolean();
            if(k){
                tmp |= 1;
            }
        }

        return tmp;
    }

    public boolean readBoolean(){

        int i = bit / 8;

        if(i >= data.length){
            throw new IllegalStateException("No more data to read");
        }

        byte b = data[i];

        int k = bit % 8;

        bit++;

        return ((b >>> (8 - k - 1)) & 1) == 1;
    }

    public int readInt(){

        int x;

        byte a = readByte();
        x = a & 0xFF;

        byte b = readByte();
        x = x << 8;
        x |= (b & 0xFF);

        byte c = readByte();
        x = x << 8;
        x |= (c & 0xFF);

        byte d = readByte();
        x = x << 8;
        x |= (d & 0xFF);

        return x;
    }

    public char readChar(){

        int x;

        byte a = readByte();
        x = a & 0xFF;

        byte b = readByte();
        x = x << 8;
        x |= (b & 0xFF);

        return (char) x;
    }
}
