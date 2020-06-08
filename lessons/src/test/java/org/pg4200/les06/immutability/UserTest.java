package org.pg4200.les06.immutability;


import org.junit.jupiter.api.Test;
import org.pg4200.les06.set.MySet;
import org.pg4200.les06.set.MySetHashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arcuri82 on 14-Sep-17.
 */
public class UserTest {


    @Test
    public void testDifferent(){

        User a = new User("a","foo", 0);
        User b = new User("b","foo", 0);

        //even if same id, they are not equal
        assertNotEquals(a, b);

        MySet<User> users = new MySetHashMap<>();
        users.add(a);
        users.add(b);

        assertEquals(2, users.size());

        //let's recreate "a" in a new instance
        User copy = new User("a","foo", 0);

        /*
            "a" and "copy" are different objects in the memory, but they are treated as equal
            because having same state
         */
        assertTrue(users.isPresent(copy));
    }

    @Test
    public void testWrongHash(){

        User lower = new UserWrongHash("foo","bar", 42);
        User upper = new UserWrongHash("FOO","BAR", 42);

        /*
            Note: depending on whether there is collision or
            not on the generated hash, the two equal objects
            could end up in two different buckets (ie positions
            in the internal array).

            So in this case, we have an inconsistency, as we would
            expect only one single element
         */
        addTwo(lower, upper, 2);
    }


    private void addTwo(User first, User second, int expectedSize){

        //assuming the two objects are the same according to equals()
        assertEquals(first, second);

        MySet<User> users = new MySetHashMap<>();
        users.add(first);
        users.add(second);

        /*
            Note: depending on whether there is collision or
            not on the generated hash, the two equal objects
            could end up in two different buckets (ie positions
            in the internal array).

            So in this case, we have an inconsistency, as we would
            expect only one single element
         */
        assertEquals(expectedSize, users.size());
    }

    @Test
    public void testCorrectHash(){

        User lower = new UserCorrectHash("foo","bar", 42);
        User upper = new UserCorrectHash("FOO","BAR", 42);

        //now it should be fine
        addTwo(lower, upper, 1);
    }


    @Test
    public void testMutabilityProblem(){

        User foo = new User("foo", "bar", 0);

        MySet<User> users = new MySetHashMap<>();
        users.add(foo);

        assertEquals(1, users.size());
        assertTrue(users.isPresent(foo));

        //now, let's change the name
        foo.setName("changed");

        //still one element
        assertEquals(1, users.size());

        /*
            unless there is an hash collision, we cannot find it any more
            as ended up in a different bucket
         */
        assertFalse(users.isPresent(foo));

        //let's create a new instance with same original data
        User copy = new User("foo", "bar", 0);

        //can't find it, because the element in the set has a different name
        assertFalse(users.isPresent(copy));

        //let's try with copy of changed
        User changedCopy = new User("changed", "bar", 0);
        assertEquals(foo, changedCopy); // should be equal

        /*
            Although equal, we still cannot find it.
            They have same equals() and hashCode(), but the problem
            is that the current "foo" in the set is in a wrong bucket
            based on its hashcode
         */
        assertFalse(users.isPresent(changedCopy));

        //now, let's change the actual value in the set, which we have in reference foo.
        foo.setName("foo"); // back to its original value

        //even if modified the reference "foo", as now the bucket is correct, we can find "copy"
        assertTrue(users.isPresent(copy));
    }


    @Test
    public void testImmutability(){

        UserImmutable foo = new UserImmutable("foo", "bar", 0);

        MySet<UserImmutable> users = new MySetHashMap<>();
        users.add(foo);

        assertEquals(1, users.size());
        assertTrue(users.isPresent(foo));

        UserImmutable changed = foo.withName("changed");

        //still one element
        assertEquals(1, users.size());

        /*
            the "withName" created a new instance, and not modified
            the one in the set
         */
        assertTrue(users.isPresent(foo));

        //let's create a new instance with same original data
        UserImmutable copy = new UserImmutable("foo", "bar", 0);

        //no problem in finding it
        assertTrue(users.isPresent(copy));
    }
}