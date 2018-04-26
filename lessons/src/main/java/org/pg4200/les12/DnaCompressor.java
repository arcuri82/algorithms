package org.pg4200.les12;

/**
 * Class used to (de)compress DNA data represented with text
 * of A, T, C, G letters.
 *
 * Created by arcuri82 on 30-Oct-17.
 */
public class DnaCompressor {

    public static byte[] compress(String dna){

        BitWriter writer = new BitWriter();

        /*
            Essential that we write the length of the sequence.
            Otherwise, the 00 in the last byte would be undefined,
            ie they could be just padding or actual T values.

            As a byte has 8 bits, and we use 2 bits per letter, each
            byte can contain 4 letters.
         */
        writer.write(dna.length());

        for(int i=0; i<dna.length(); i++){
            char c = dna.charAt(i);

            /*
                Using 2 bits per letter
             */

            if(c== 'A'){  //11
                writer.write(true);
                writer.write(true);
            } else if(c== 'C'){ //10
                writer.write(true);
                writer.write(false);
            }else if(c== 'G'){ //01
                writer.write(false);
                writer.write(true);
            }else if(c== 'T'){ //00
                writer.write(false);
                writer.write(false);
            } else {
                throw new IllegalArgumentException("Unrecognized character: " + c);
            }
        }

        return writer.extract();
    }

    public static String decompress(byte[] data){

        BitReader reader = new BitReader(data);

        int n = reader.readInt();

        StringBuffer buffer = new StringBuffer(n);

        for(int i=0; i<n; i++){

            boolean a = reader.readBoolean();
            boolean b = reader.readBoolean();

            if(a && b){
                buffer.append("A");
            } else if(a && !b){
                buffer.append("C");
            }else if(!a && b){
                buffer.append("G");
            }else if(!a && !b){
                buffer.append("T");
            }
        }

        return buffer.toString();
    }
}
