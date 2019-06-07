package org.pg4200.ex07;

import org.junit.jupiter.api.Test;
import org.pg4200.les06.set.MySet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arcuri82 on 07-Jun-19.
 */
public abstract class ExtendedListTestTemplate {

    protected abstract <T> ExtendedList<T> getInstance(Class<T> klass);

    /*
        these are the same kind of tests used for the Stream version
     */

    @Test
    public void testForEach() {

        ExtendedList<String> list = getInstance(String.class);
        list.add("a");
        list.add("b");
        list.add("c");

        ExtendedList<String> upper = getInstance(String.class);

        list.forEach(s -> upper.add(s.toUpperCase()));

        assertEquals(3, upper.size());
        assertEquals("A", upper.get(0));
        assertEquals("B", upper.get(1));
        assertEquals("C", upper.get(2));
    }


    @Test
    public void testFilter() {

        ExtendedList<String> list = getInstance(String.class);
        list.add("x");
        list.add("y");
        list.add("foo");
        list.add("bar");
        list.add("c");

        ExtendedList<String> longAtLeastThree = list.filter(s -> s.length() >= 3);


        assertEquals(2, longAtLeastThree.size());
        assertEquals("foo", longAtLeastThree.get(0));
        assertEquals("bar", longAtLeastThree.get(1));

        //no side effect on original list
        assertEquals(5, list.size());
    }

    private class User {
        public String name;
        public String surname;
        public String nationality;

        public User(String name, String surname, String nationality) {
            this.name = name;
            this.surname = surname;
            this.nationality = nationality;
        }
    }

    private ExtendedList<User> getSomeUsers() {
        ExtendedList<User> list = getInstance(User.class);
        list.add(new User("John", "Smith", "USA"));
        list.add(new User("Foo", "Hansen", "Norway"));
        list.add(new User("Bar", "Olsen", "Norway"));
        list.add(new User("Marco", "Rossi", "Italy"));
        return list;
    }

    @Test
    public void testMap() {

        ExtendedList<User> list = getSomeUsers();

        ExtendedList<String> names = list.map(u -> u.name);

        assertEquals(4, names.size());
        assertEquals("John", names.get(0));
        assertEquals("Foo", names.get(1));
        assertEquals("Bar", names.get(2));
        assertEquals("Marco", names.get(3));
    }


    @Test
    public void testFilteredMap() {

        ExtendedList<User> list = getSomeUsers();

        ExtendedList<String> names = list
                .filter(u -> u.nationality.equalsIgnoreCase("norway"))
                .map(u -> u.name);


        assertEquals(2, names.size());
        assertEquals("Foo", names.get(0));
        assertEquals("Bar", names.get(1));

        //no side effect on original list
        assertEquals(4, list.size());
    }


    @Test
    public void testFlatMap() {

        ExtendedList<ExtendedList> all = getInstance(ExtendedList.class);

        ExtendedList<User> norway = getInstance(User.class);
        norway.add(new User("Foo", "Hansen", "Norway"));
        norway.add(new User("Bar", "Olsen", "Norway"));

        ExtendedList<User> usa = getInstance(User.class);
        usa.add(new User("John", "Smith", "USA"));
        usa.add(new User("Robert", "Black", "USA"));

        ExtendedList<User> italy = getInstance(User.class);
        italy.add(new User("Marco", "Rossi", "Italy"));

        all.add(norway);
        all.add(usa);
        all.add(italy);


        MySet<String> surnames = all
                .flatMap(l -> (ExtendedList<User>) l)
                .map(u -> u.surname)
                .toSet();

        assertEquals(5, surnames.size());

        assertTrue(surnames.isPresent("Hansen"));
        assertTrue(surnames.isPresent("Olsen"));
        assertTrue(surnames.isPresent("Smith"));
        assertTrue(surnames.isPresent("Black"));
        assertTrue(surnames.isPresent("Rossi"));

        //no side effect on original list
        assertEquals(3, all.size());
    }
}