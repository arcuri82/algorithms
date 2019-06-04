package org.pg4200.les02.list;

/**
 * Created by arcuri82 on 15-Aug-17.
 */
public class MyArrayList<T> implements MyList<T> {

    protected Object[] data;
    protected int size = 0;

    public MyArrayList(){
        this(10);
    }

    public MyArrayList(int capacity){
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
    public void add(int index, T value) {

        if(index < 0 || index > size){
            //note that here "size" is a valid index
            throw new IndexOutOfBoundsException();
        }

        /*
            First we need to shift-right all elements starting from index.
            However, we need to make sure to do not overwrite existing elements
            before we shift. That is the reason why we must start from the end
            of the the list
         */
        for(int j=size-1; j>=index; j--){
            data[j+1] = data[j];
        }

        /*
            What if index >= data.length???
            This will be addressed in the exercises.
         */

        data[index] = value;
        size++;
    }

    @Override
    public int size() {
        return size;
    }
}
