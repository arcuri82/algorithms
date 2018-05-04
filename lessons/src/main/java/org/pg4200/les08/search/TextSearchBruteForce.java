package org.pg4200.les08.search;

/**
 * Created by arcuri82 on 04-May-18.
 */
public class TextSearchBruteForce implements TextSearch {

    private final String defaultToken;

    public TextSearchBruteForce() {
        defaultToken = null;
    }

    public TextSearchBruteForce(String defaultToken) {
        this.defaultToken = defaultToken;
    }

    @Override
    public int findFirst(String text) {
        if(defaultToken == null){
            throw new IllegalStateException("No default token specified");
        }

        return findFirst(text, defaultToken);
    }

    @Override
    public int findFirst(String text, String token) {

        if (text == null || token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }

        if (token.length() > text.length()) {
            //simple case
            return -1;
        }

        outer:
        for (int i = 0; i < text.length(); i++) {

            for (int j = 0; j < token.length(); j++) {
                if (text.charAt(i + j) != token.charAt(j)) {
                    continue outer;
                }
            }

            return i;
        }

        return -1;
    }
}
