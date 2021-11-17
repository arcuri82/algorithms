package org.pg4200.ex11;

import org.pg4200.les11.BitReader;
import org.pg4200.les11.BitWriter;

public class GradeCompressionImpl implements GradeCompressor{
    @Override
    public byte[] compress(String idsAndGrades) {

        BitWriter writer = new BitWriter();
        String id = "";

        for(int i = 0; i< idsAndGrades.length(); i++){
            char c = idsAndGrades.charAt(i);
            if(c >= '0' && c <= '9'){
                //clearly a figure
                id += c;
            }

            if(c >= 'A' && c <= 'F'){
                // this is clearly a grade

                int numericalId = Integer.parseInt(id);
                int numericalGrade = c - 'A';
                id = "";

                writer.write(numericalId, 9);
                writer.write(numericalGrade, 3);
                // after reading this, the entry for 1 student is done
                // I can start writing on the byte array
            }
        }

        return writer.extract();
    }

    @Override
    public String decompress(byte[] data) {

        BitReader reader = new BitReader(data);
        String result = "";
        int entries = data.length*8/12;

        for(int i = 0; i < entries; i++){
            int id = reader.readInt(9);
            int grade = (char) ('A' + reader.readInt(3));

            result += id;
            result += (char) grade;
        }

        return result;
    }
}
