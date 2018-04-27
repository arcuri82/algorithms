package org.pg4200.ex01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class ArrayUtilsTestTemplate {

    protected abstract ArrayUtils getNewInstance();

    protected ArrayUtils utils;

    @BeforeEach
    public void init(){
        utils = getNewInstance();
    }

    @Test
    public void testEmptyArray(){

        assertThrows(IllegalArgumentException.class,
                () -> utils.min(null));
        assertThrows(IllegalArgumentException.class,
                () -> utils.max(null));
        assertThrows(IllegalArgumentException.class,
                () -> utils.mean(null));

        assertThrows(IllegalArgumentException.class,
                () -> utils.min(new int[]{}));
        assertThrows(IllegalArgumentException.class,
                () -> utils.max(new int[]{}));
        assertThrows(IllegalArgumentException.class,
                () -> utils.mean(new int[]{}));
    }

    @Test
    public void testMinPositive(){

        int[] array = {5,4,1,7};

        int res = utils.min(array);

        assertEquals(1, res);
    }

    @Test
    public void testMinNegative(){

        int[] array = {5,-4,1,-7,0};

        int res = utils.min(array);

        assertEquals(-7, res);
    }

    @Test
    public void testMaxPositive(){

        int[] array = {5,4,1,7,0};

        int res = utils.max(array);

        assertEquals(7, res);
    }

    @Test
    public void testMaxNegative(){

        int[] array = {-5,-4,-1,-7};

        int res = utils.max(array);

        assertEquals(-1, res);
    }

    @Test
    public void testMeanPositive(){

        int[] array = {2,3,4,3};

        double res = utils.mean(array);

        assertEquals(3d, res);
    }

    @Test
    public void testMeanNegative(){

        int[] array = {-2,3,4,-3,3,-11};

        double res = utils.mean(array);

        assertEquals(-1d, res);
    }

}