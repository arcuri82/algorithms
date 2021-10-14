package org.pg4200.ex11;

public interface GradeCompressor {

    /**
     *
     * @param idsAndGrades string list in the form id:grade, eg, 0A1F2C
     * @return compressed bytes
     */
    byte[] compress(String idsAndGrades);

    String decompress(byte[] data);

}
