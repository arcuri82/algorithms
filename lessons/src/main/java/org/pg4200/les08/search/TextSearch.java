package org.pg4200.les08.search;

/**
 * Created by arcuri82 on 04-May-18.
 */
public interface TextSearch {

    int findFirst(String text);

    int findFirst(String text, String token);
}
