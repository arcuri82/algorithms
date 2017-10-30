package org.pg4200.compression;

/**
 * Created by arcuri82 on 30-Oct-17.
 */
public class DnaCompressor {

    public byte[] compress(String dna){

        BitWriter writer = new BitWriter();

        writer.write(dna.length());

        for(int i=0; i<dna.length(); i++){
            char c = dna.charAt(i);

            if(c== 'A'){
                writer.write(true);
                writer.write(true);
            } else if(c== 'C'){
                writer.write(true);
                writer.write(false);
            }else if(c== 'G'){
                writer.write(false);
                writer.write(true);
            }else if(c== 'T'){
                writer.write(false);
                writer.write(false);
            } else {
                throw new IllegalArgumentException("Unrecognized character: " + c);
            }
        }

        return writer.extract();
    }

    public String decompress(byte[] data){

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
