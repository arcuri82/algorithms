package org.pg4200.les09.search;

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
    public int findFirst(String text) throws IllegalStateException{
        if(defaultToken == null){
            throw new IllegalStateException("No default target specified");
        }

        return findFirst(text, defaultToken);
    }

    @Override
    public int findFirst(String text, String target) {

        if (text == null || target == null || target.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }

        if (target.length() > text.length()) {
            //simple case
            return -1;
        }

        //check each position in text as starting point for target
        outer: for (int i = 0; i < text.length(); i++) {

            //check chars of target based on current starting point in text
            for (int j = 0; j < target.length(); j++) {
                if (text.charAt(i + j) != target.charAt(j)) {
                    /*
                        this will jump out of this "for",
                        and continue to the next step in the
                        "for" labelled with "outer"
                      */
                    continue outer;
                }
            }

            return i;
        }

        //found nothing
        return -1;
    }
}
