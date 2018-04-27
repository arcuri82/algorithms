package org.pg4200.les05;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by arcuri82 on 21-Aug-17.
 */
public class SearchTest {


    @Test
    public void testFind() throws Exception {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

        for(int i=0; i<list.size(); i++){
            assertEquals(i, Search.findInSorted(list, i+1));
            assertEquals(i, Search.findInNonSorted(list, i+1));
        }
    }

}