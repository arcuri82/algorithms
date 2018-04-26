package org.pg4200.les02.delete;

/**
 * Created by arcuri82 on 15-Aug-17.
 */
public class ArrayDeleteContainer<T> implements DeleteContainer<T> {

    protected Object[] data;
    protected int size = 0;

    public ArrayDeleteContainer(){
        this(10);
    }

    public ArrayDeleteContainer(int capacity){
        data = new Object[capacity];
    }

    @Override
    public void delete(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        data[index] = null;

        /*
            If we remove the first element, this can be quite
            expensive, as we need to move to the left all the
            others in the container
         */

        for(int i = index; i < size-1; i++){
            //shift all values to the left
            data[i] = data[i+1];

            /*
                eg, if we remove first, from
                [1 2 3 4]
                we get
                [2 3 4] 4

                Note: we use "size" to tell how long is the list.
                If there is data in the list after size (eg the last 4 above),
                that does not really matter for the list.
                However, we still need to put it to "null" to avoid
                memory leaks (but this would be a long discussion about
                how "garbage collection" works...)
             */
        }

        size--;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        return (T) data[index];
    }

    @Override
    public void add(T value) {
        data[size] = value;
        size++;
    }

    @Override
    public int size() {
        return size;
    }
}
