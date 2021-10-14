package org.pg4200.sol10;

import org.pg4200.les10.search.TextSearch;
import org.pg4200.les10.search.TextSearchTestTemplate;

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