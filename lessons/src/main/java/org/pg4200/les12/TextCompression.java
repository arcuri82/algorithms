package org.pg4200.les12;

/**
 * Created by arcuri82 on 03-May-18.
 */
public interface TextCompression {

    byte[] compress(String text);

    String decompress(byte[] data);

    /**
     * Get some statistics as a String with several lines.
     */
    String getStatistics(String text);
}
