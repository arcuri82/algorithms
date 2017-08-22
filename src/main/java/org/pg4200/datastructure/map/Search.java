package org.pg4200.datastructure.map;

import java.util.Comparator;
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
    }

}
