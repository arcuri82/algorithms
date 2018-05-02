package org.pg4200.ex07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arcuri82 on 02-May-18.
 */
public  abstract class ComputationExampleTestTemplate {


    protected abstract ComputationExample getNewInstance();

    private ComputationExample example;


    @BeforeEach
    public void init(){
        example = getNewInstance();
    }


    @Test
    public void testEmpty(){

        List<String> list = example.compute(new ArrayList<>());

        assertEquals(0, list.size());
    }

    //TODO
}