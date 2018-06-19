package org.pg4200.les09.search;

/**
 * Created by arcuri82 on 04-May-18.
 */
public interface TextSearch {

    /**
     * Find if given token is in the text
     *
     * @param text  the String text to search in
     * @param token  the String that we want to check if contained in the text
     * @return the index of first character starting the token inside the text,
     *         otherwise a negative number if token is not present.
     */
    int findFirst(String text, String token);

    /**
     * Find if the default token for this class is in the text.
     * As the code can do specific optimizations on the token, it could
     * do some pre-computation when this class is instantiated.
     *
     * @param text the String text to search in
     * @return the index of first character starting the default token inside the text,
     *         otherwise a negative number if token is not present.
     * @throws IllegalStateException if no default token was defined
     */
    int findFirst(String text) throws IllegalStateException;
}
