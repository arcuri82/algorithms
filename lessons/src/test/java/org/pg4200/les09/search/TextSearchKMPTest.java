package org.pg4200.les09.search;

/**
 * Created by arcuri82 on 04-May-18.
 */
public class TextSearchKMPTest extends TextSearchTestTemplate{

    @Override
    protected TextSearch getNewInstance() {
        return new TextSearchKMP();
    }

    @Override
    protected TextSearch getNewInstance(String target) {
        return new TextSearchKMP(target);
    }
}