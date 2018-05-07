package org.pg4200.les09.search;

/**
 * Created by arcuri82 on 04-May-18.
 */
public class TextSearchKMP implements TextSearch {

    private final int[][] backtrace;
    protected final String defaultToken;

    public TextSearchKMP() {
        backtrace = null;
        defaultToken = null;
    }

    public TextSearchKMP(String token) {

        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }

        defaultToken = token;

        backtrace = new int[65_536][token.length()];

        backtrace[token.charAt(0)][0] = 1;

        for(int x=0, j=1; j<token.length(); j++){
            for(int c=0; c<backtrace.length; c++){
                backtrace[c][j] = backtrace[c][x];
            }
            backtrace[token.charAt(j)][j] = j+1;
            x = backtrace[token.charAt(j)][x];
        }
    }


    @Override
    public int findFirst(String text) {

        if(defaultToken == null || backtrace == null){
            throw new IllegalStateException("No default token specified");
        }

        int i, j;

        for(i=0, j=0; i<text.length() && j< defaultToken.length(); i++){
            j = backtrace[text.charAt(i)][j];
        }

        if(j == defaultToken.length()){
            return i - defaultToken.length();
        }

        return -1;
    }

    @Override
    public int findFirst(String text, String token) {

        /*
         * Note: this is quite inefficient... building a "backtrace"
         * data structure is not cheap... if we throw it away at the
         * first search, then it could be faster to just do a brute
         * force search
         */
        TextSearchKMP textSearchKMP = new TextSearchKMP(token);

        return textSearchKMP.findFirst(text);
    }
}
