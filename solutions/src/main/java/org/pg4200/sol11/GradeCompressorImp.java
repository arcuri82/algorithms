package org.pg4200.sol11;

import org.pg4200.ex11.GradeCompressor;
import org.pg4200.les11.BitReader;
import org.pg4200.les11.BitWriter;

public class GradeCompressorImp implements GradeCompressor {

    @Override
    public byte[] compress(String idsAndGrades) {

        BitWriter writer = new BitWriter();
        String id = "";

        for(int i=0; i<idsAndGrades.length(); i++){
            char c = idsAndGrades.charAt(i);
            if(c >= '0' && c <= '9'){
                id += c;
            } else if( c >= 'A' && c <= 'F'){
                writer.write(Integer.parseInt(id), 9);
                writer.write(c - 'A', 3);
                id = "";
            }
        }

        return writer.extract();
    }

    @Override
    public String decompress(byte[] data) {

        BitReader reader = new BitReader(data);
        String result = "";
        int entries = (data.length * 8) / 12;

        for(int i=0; i<entries; i++){
            result += reader.readInt(9);
            result += (char)('A' + reader.readInt(3));
        }

        return result;
    }
}
