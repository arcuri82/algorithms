package org.pg4200.ex10;

import org.pg4200.les10.search.TextSearchKMP;

import javax.print.attribute.HashAttributeSet;
import java.util.HashMap;

public class TextSearchKMPCached extends TextSearchKMP {

    private HashMap<String, TextSearchKMP> cache;

    public TextSearchKMPCached(){
        super();
        this.cache = new HashMap<String, TextSearchKMP>();
    }

    public TextSearchKMPCached(String target){
        super(target);
        this.cache = new HashMap<String, TextSearchKMP>();
    }

    @Override
    public int findFirst(String text, String target){

        if(!cache.containsKey(target)){
            cache.put(target, new TextSearchKMP(target));
        }

        return cache.get(target).findFirst(text);

        //return -1;
    }
}
