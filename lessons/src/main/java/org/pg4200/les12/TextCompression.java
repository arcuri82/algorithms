package org.pg4200.les12;

/**
 * Created by arcuri82 on 03-May-18.
 */
public interface TextCompression {

    /**
     *  Given an input text, compress it, and return such
     *  compressed data as an array of bytes
     */
    byte[] compress(String text);

    /**
     * Given a previously compressed byte array, decompress it
     * and return it as a string
     */
    String decompress(byte[] data);

    /**
     * Get some statistics as a String with several lines.
     */
    String getStatistics(String text);
}
