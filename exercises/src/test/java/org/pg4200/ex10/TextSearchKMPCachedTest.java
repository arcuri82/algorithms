package org.pg4200.ex10;

import org.pg4200.les10.search.TextSearch;
import org.pg4200.les10.search.TextSearchTestTemplate;

public class TextSearchKMPCachedTest extends TextSearchTestTemplate{
    @Override
    protected TextSearch getNewInstance() {
        return new TextSearchKMPCached();
    }

    @Override
    protected TextSearch getNewInstance(String target) {
        return new TextSearchKMPCached(target);
    }
}
