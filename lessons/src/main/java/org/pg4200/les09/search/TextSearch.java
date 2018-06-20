package org.pg4200.les09.search;

/**
 * Created by arcuri82 on 04-May-18.
 */
public interface TextSearch {

    /**
     * Find if given target is in the text
     *
     * @param text  the String text to search in
     * @param target  the String that we want to check if contained in the text
     * @return the index of first character starting the target inside the text,
     *         otherwise a negative number if target is not present.
     */
    int findFirst(String text, String target);

    /**
     * Find if the default target for this class is in the text.
     * As the code can do specific optimizations on the target, it could
     * do some pre-computation when this class is instantiated.
     *
     * @param text the String text to search in
     * @return the index of first character starting the default target inside the text,
     *         otherwise a negative number if target is not present.
     * @throws IllegalStateException if no default target was defined
     */
    int findFirst(String text) throws IllegalStateException;
}
