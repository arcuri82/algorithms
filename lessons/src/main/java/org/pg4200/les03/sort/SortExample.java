package org.pg4200.les03.sort;

import java.util.Arrays;

/**
 * Created by arcuri82 on 21-Aug-17.
 */
public class SortExample {

    public static void main(String[] args){

        String[] names = {
                "Sara",
                "Jack",
                "Robert",
                "John",
                "Elisabeth",
                "Per",
                "Erik",
                "Richard",
                "Maria",
                "Bob"
        };

        /*
         * Practically all languages have built-in APIs to sort
         * collections, like arrays.
         * You will not need to implement sorting in your programs,
         * but need to understand how it works.
         *
         * How is sorting implemented? What is its complexity?
         * Ie, if I double the size, will it take twice as long to sort?
         * (Answer: no, likely it will take more than twice as long)
         */
        Arrays.sort(names);

        for(String s : names){
            System.out.println(s);
        }
    }

}
