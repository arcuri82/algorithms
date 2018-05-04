package org.pg4200.les08.search;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arcuri82 on 04-May-18.
 */
public class TextSearchBruteForceTest extends TextSearchTestTemplate{


    @Override
    protected TextSearch getNewInstance() {
        return new TextSearchBruteForce();
    }

    @Override
    protected TextSearch getNewInstance(String token) {
        return new TextSearchBruteForce(token);
    }
}