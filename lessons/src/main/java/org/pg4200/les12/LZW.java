package org.pg4200.les12;

import java.util.Objects;

/**
 * Created by arcuri82 on 03-May-18.
 */
public class LZW implements TextCompression {

    private static final int ASCII_LIMIT = 128; // 2^7

    private static final int EOF = ASCII_LIMIT;

    private static final int CODEWORD_BITS = 12;

    private static final int NUMBER_OF_CODEWORDS = 4096; // 2^CODEWORD_BITS = 2^12

    @Override
    public byte[] compress(String text) {

        Objects.requireNonNull(text);

        MyTernarySearchTrie<Integer> trie = new MyTernarySearchTrie<>();

        for (int i = 0; i < ASCII_LIMIT; i++) {
            trie.put("" + (char) i, i);
        }

        int code = EOF + 1;

        BitWriter buffer = new BitWriter();

        while (text.length() > 0) {

            String longestPrefix = trie.longestPrefixOf(text);
            if (longestPrefix.isEmpty()) {
                //this could happen if as input we have a non-ascii code
                int invalid = text.charAt(0);
                throw new IllegalArgumentException("Cannot handle character with code: " + invalid);
            }
            int codeOfLongestPrefix = trie.get(longestPrefix);

            buffer.write(codeOfLongestPrefix, CODEWORD_BITS);

            int len = longestPrefix.length();
            if (len < text.length() && code < NUMBER_OF_CODEWORDS) {
                //create new entry
                String newToken = text.substring(0, len + 1);
                trie.put(newToken, code++);
            }

            /*
                the first "len" characters are removed before
                going into next iterations of the loop
             */

            text = text.substring(len);
        }

        buffer.write(EOF, CODEWORD_BITS);

        return buffer.extract();
    }

    @Override
    public String decompress(byte[] data) {

        BitReader reader = new BitReader(data);
        StringBuilder buffer = new StringBuilder();

        String[] mapping = new String[NUMBER_OF_CODEWORDS];

        int codeCounter = 0;

        for (; codeCounter < ASCII_LIMIT; codeCounter++) {
            mapping[codeCounter] = "" + (char) codeCounter;
        }
        mapping[codeCounter++] = " "; //unused, for EOF

        int tokenCode = reader.readInt(CODEWORD_BITS);
        String valueToWrite = mapping[tokenCode];

        while (true) {
            buffer.append(valueToWrite);

            tokenCode = reader.readInt(CODEWORD_BITS);
            if (tokenCode == EOF) {
                break;
            }

            String token = mapping[tokenCode];

            if (tokenCode == codeCounter) {
                token = valueToWrite + valueToWrite.charAt(0);
            }
            if (codeCounter < NUMBER_OF_CODEWORDS) {
                mapping[codeCounter++] = valueToWrite + token.charAt(0);
            }

            valueToWrite = token;
        }

        return buffer.toString();
    }

    @Override
    public String getStatistics(String text) {
        return null; //TODO
    }
}
