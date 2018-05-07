package org.pg4200.sol09;

import org.pg4200.les09.search.TextSearch;
import org.pg4200.les09.search.TextSearchTestTemplate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arcuri82 on 07-May-18.
 */
public class TextSearchKMPCachedTest extends TextSearchTestTemplate {

    @Override
    protected TextSearch getNewInstance() {
        return new TextSearchKMPCached();
    }

    @Override
    protected TextSearch getNewInstance(String token) {
        return new TextSearchKMPCached(token);
    }
}