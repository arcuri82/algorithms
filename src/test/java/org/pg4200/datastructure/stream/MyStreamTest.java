package org.pg4200.datastructure.stream;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by arcuri82 on 04-Oct-17.
 */
public class MyStreamTest {


    @Test
    public void testCollect() {

        StreamList<String> list = new StreamList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        StreamList<String> copy = list.stream().collectToList();

        assertEquals(list.size(), copy.size());

        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i), copy.get(i));
        }
    }

    @Test
    public void testForEach() {

        StreamList<String> list = new StreamList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        StreamList<String> upper = new StreamList<>();

        list.stream().forEach(s -> upper.add(s.toUpperCase()));

        assertEquals(3, upper.size());
        assertEquals("A", upper.get(0));
        assertEquals("B", upper.get(1));
        assertEquals("C", upper.get(2));
    }


    @Test
    public void testFilter() {

        StreamList<String> list = new StreamList<>();
        list.add("x");
        list.add("y");
        list.add("foo");
        list.add("bar");
        list.add("c");

        StreamList<String> longAtLeastThree = list.stream()
                .filter(s -> s.length() >= 3)
                .collectToList();

        assertEquals(2, longAtLeastThree.size());
        assertEquals("foo", longAtLeastThree.get(0));
        assertEquals("bar", longAtLeastThree.get(1));
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

    private StreamList<User> getSomeUsers() {
        StreamList<User> list = new StreamList<>();
        list.add(new User("John", "Smith", "USA"));
        list.add(new User("Foo", "Hansen", "Norway"));
        list.add(new User("Bar", "Olsen", "Norway"));
        list.add(new User("Marco", "Rossi", "Italy"));
        return list;
    }

    @Test
    public void testMap() {

        StreamList<User> list = getSomeUsers();

        StreamList<String> names = list.stream()
                .map(u -> u.name)
                .collectToList();

        assertEquals(4, names.size());
        assertEquals("John", names.get(0));
        assertEquals("Foo", names.get(1));
        assertEquals("Bar", names.get(2));
        assertEquals("Marco", names.get(3));
    }


    @Test
    public void testFilteredMap() {

        StreamList<User> list = getSomeUsers();

        StreamList<String> names = list.stream()
                .filter(u -> u.nationality.equalsIgnoreCase("norway"))
                .map(u -> u.name)
                .collectToList();

        assertEquals(2, names.size());
        assertEquals("Foo", names.get(0));
        assertEquals("Bar", names.get(1));
    }

    @Test
    public void testFilteredMapWithoutStream() {

        StreamList<User> list = getSomeUsers();

        StreamList<String> names = new StreamList();
        for (User user : list) {
            if (!user.nationality.equalsIgnoreCase("norway")) {
                continue;
            }
            names.add(user.name);
        }

        assertEquals(2, names.size());
        assertEquals("Foo", names.get(0));
        assertEquals("Bar", names.get(1));
    }


    @Test
    public void testFlatMap() {

        StreamHashMap<String, StreamList<User>> usersByNationality = new StreamHashMap<>();

        StreamList<User> norway = new StreamList<>();
        norway.add(new User("Foo", "Hansen", "Norway"));
        norway.add(new User("Bar", "Olsen", "Norway"));

        StreamList<User> usa = new StreamList<>();
        usa.add(new User("John", "Smith", "USA"));
        usa.add(new User("Robert", "Black", "USA"));

        StreamList<User> italy = new StreamList<>();
        italy.add(new User("Marco", "Rossi", "Italy"));

        usersByNationality.put("Norway", norway);
        usersByNationality.put("USA", usa);
        usersByNationality.put("Italy", italy);

        /*
            Now let's get surname from all countries
         */

        StreamList<String> surnames = usersByNationality.stream()
                .flatMap(l -> l.stream())
                .map(u -> u.surname)
                .collectToList();

        assertEquals(5, surnames.size());
        /*
            Note: due to iteration on a hash map,
            cannot guarantee the order of those surname
            in the list
         */
        assertTrue(surnames.contains("Hansen"));
        assertTrue(surnames.contains("Olsen"));
        assertTrue(surnames.contains("Smith"));
        assertTrue(surnames.contains("Black"));
        assertTrue(surnames.contains("Rossi"));
    }
}