package org.pg4200.ex02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MiddleLinkedListTest {

    @Test
    public void addTest(){
        MiddleLinkedList<Integer> middleList = new MiddleLinkedList<>();

        middleList.add(0, 42);
        middleList.add(0, 12);
        middleList.add(0, 6);
        middleList.add(2, 104);
        middleList.add(2, 84);
        middleList.add(1, 8);

        assertEquals(6, middleList.size);
    }
}
