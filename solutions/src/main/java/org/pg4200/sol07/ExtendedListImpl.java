package org.pg4200.sol07;

import org.pg4200.ex07.ExtendedList;
import org.pg4200.les02.list.MyArrayList;
import org.pg4200.les06.set.MySet;
import org.pg4200.les06.set.MySetHashMap;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by arcuri82 on 07-Jun-19.
 */
public class ExtendedListImpl<T> extends MyArrayList<T> implements ExtendedList<T> {


    @Override
    public ExtendedList<T> filter(Predicate<T> predicate) {

        ExtendedList<T> other = new ExtendedListImpl<>();

        for(int i=0; i<this.size; i++){
            T value = this.get(i);
            if(predicate.test(value)){
                other.add(value);
            }
        }

        return other;
    }

    @Override
    public <R> ExtendedList<R> map(Function<T, R> mapper) {

        ExtendedList<R> other = new ExtendedListImpl<>();

        for(int i=0; i<this.size; i++){
            T value = this.get(i);
            other.add(mapper.apply(value));
        }

        return other;
    }

    @Override
    public <R> ExtendedList<R> flatMap(Function<T, ExtendedList<R>> mapper) {

        ExtendedList<R> other = new ExtendedListImpl<>();

        for(int i=0; i<this.size; i++){
            T value = this.get(i);
            ExtendedList<R> list = mapper.apply(value);
            for(int j=0; j<list.size(); j++){
                other.add(list.get(j));
            }
        }

        return other;
    }

    @Override
    public void forEach(Consumer<T> action) {

        for(int i=0; i<this.size; i++) {
            T value = this.get(i);
            action.accept(value);
        }
    }

    @Override
    public MySet<T> toSet() {

        MySet<T> set = new MySetHashMap<>();

        for(int i=0; i<this.size; i++) {
            T value = this.get(i);
            set.add(value);
        }

        return set;
    }
}
