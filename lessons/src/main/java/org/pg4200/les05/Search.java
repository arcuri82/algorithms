package org.pg4200.les05;

import java.util.List;

/**
 * Created by arcuri82 on 21-Aug-17.
 */
public class Search {


    public static <T extends Comparable<T>> int findInNonSorted(List<T> list, T element){

        if(list == null || list.isEmpty() || element == null){
            return -1;
        }

        for(int i=0; i<list.size(); i++){
            T value = list.get(i);
            if(element.compareTo(value) == 0){
                return i;
            }
        }

        return -1;
    }


    public static <T extends Comparable<T>> int findInSorted(List<T> list, T element){

        if(list == null || list.isEmpty() || element == null){
            return -1;
        }

        return binarySearch(0, list.size()-1, list, element);
    }

    private static <T extends Comparable<T>> int binarySearch(int low, int high, List<T> list, T element){

        if(low > high){
            return -1;
        }

        /*
            middle = low + (high - low) / 2
                   = (2low + high - low) / 2
                   = (low + high) / 2
         */

        int middle = (low + high) / 2;
        T value = list.get(middle);

        int comp = element.compareTo(value);

        if(comp == 0){
            return middle;
        } else if(comp < 0){
            return binarySearch(low, middle-1, list, element);
        } else {
            return binarySearch(middle+1, high, list, element);
        }

        /*
            What is the complexity of this algorithm???
            Informally, at each step we only consider half of input range.
            The recursion ends when low>high, ie when range is 0.

            Given a value N, how many times x can we divide it by 2 before
            we get a value <=1 ???

            N = 2^x
            2^x = N
            x = log(N)

            So we can say that binary search is upper-bounded by O(log n), which
            is much, much better than O(n)
         */
    }

}
