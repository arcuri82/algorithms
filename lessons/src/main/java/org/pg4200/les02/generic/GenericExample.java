package org.pg4200.les02.generic;

/**
 * The name chosen for the generic types does not really matter, but T (for "Type")
 * is a common choice.
 *
 * Here, the class is associated with a generic type T that I can use for
 * all of the methods
 *
 * Created by arcuri82 on 07-Jun-18.
 */
public class GenericExample<T> {

    public Object identityObject(Object x){
        return x;
    }

    public T identityGeneric(T x){
        return x;
    }

    /*
        Besides declaring a generic type for the whole class, we can also have it
        explicitly on the methods.
        This is done by declaring them (eg, "<Z>") between the access-right keyword (eg, "public")
        and the return type of the function (eg, "Z").
     */
    public <Z> Z identityGenericOnMethod(T t, Z z){
        return z;
    }

    /*
        Can define more than one generic type on same class
     */
    public static class MyPair<X,Y>{
        public final X x;
        public final Y y;

        public MyPair(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }

    /*
        Here we create a MyPair object, where the element x
        has to have the same type bound to the generic in the
        class GenericExample, whereas y could be anything
     */
    public <Z> MyPair<T,Z> createPair(T t, Z z){

        //on right-side, I can use <> instead of repeating <T, Z>
        MyPair<T, Z> pair = new MyPair<>(t, z);

        return pair;
    }

    /*
        Interface Comparable is part of the JDK API, and it is used
        to specify if an object is smaller, equal or greater than
        another one.
        Many class in the JDK API do implement this interface.
     */
    public Comparable max(Comparable x, Comparable y){

        if(x.compareTo(y) > 0){
            return x;
        } else {
            return y;
        }
    }

    /*
        Returning a type Comparable is a problem, as we lose info of
        the original type (eg String).
        So, here we define a generic Z that does extend Comparable<Z>.
        This allows us to call compareTo() on Z objects, and then return
        Z as result (and not the interface Comparable)
     */
    public <Z extends Comparable<Z>>  Z  maxWithGenerics(Z x, Z y){

        if(x.compareTo(y) > 0){
            return x;
        } else {
            return y;
        }
    }

}
