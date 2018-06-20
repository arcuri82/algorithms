package org.pg4200.les09.search;

/**
 * Created by arcuri82 on 04-May-18.
 */
public class TextSearchBruteForceTest extends TextSearchTestTemplate{


    @Override
    protected TextSearch getNewInstance() {
        return new TextSearchBruteForce();
    }

    @Override
    protected TextSearch getNewInstance(String target) {
        return new TextSearchBruteForce(target);
    }
}