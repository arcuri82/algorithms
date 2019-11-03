package org.pg4200.les12.lzw;

import org.junit.jupiter.api.Test;
import org.pg4200.les12.lzw.MyTernarySearchTrie;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arcuri82 on 03-May-18.
 */
public class MyTernarySearchTrieTest {

    @Test
    public void testBasicInsertion() {

        MyTernarySearchTrie<Integer> trie = new MyTernarySearchTrie<>();

        String key = "foo";
        Integer value = 42;

        assertNull(trie.get(key));

        trie.put(key, value);

        assertEquals(value, trie.get(key));
    }

    @Test
    public void testInsertNoOverlap() {

        MyTernarySearchTrie<Integer> trie = new MyTernarySearchTrie<>();

        String first_key = "foo";
        Integer first_value = 42;
        trie.put(first_key, first_value);

        String second_key = "bar";
        Integer second_value = 66;
        trie.put(second_key, second_value);

        assertEquals(first_value, trie.get(first_key));
        assertEquals(second_value, trie.get(second_key));
    }

    @Test
    public void testOverlap() {

        MyTernarySearchTrie<Integer> trie = new MyTernarySearchTrie<>();

        String first_key = "foo";
        Integer first_value = 42;
        trie.put(first_key, first_value);

        String second_key = "far";
        Integer second_value = 66;
        trie.put(second_key, second_value);

        assertEquals(first_value, trie.get(first_key));
        assertEquals(second_value, trie.get(second_key));
    }

    @Test
    public void testContinuation() {

        MyTernarySearchTrie<Integer> trie = new MyTernarySearchTrie<>();

        String first_key = "foo";
        Integer first_value = 42;
        trie.put(first_key, first_value);

        String second_key = "foobar";
        Integer second_value = 66;
        trie.put(second_key, second_value);

        assertEquals(first_value, trie.get(first_key));
        assertEquals(second_value, trie.get(second_key));
    }

    @Test
    public void testThreeDistinct() {

        MyTernarySearchTrie<Integer> trie = new MyTernarySearchTrie<>();

        String first_key = "abc";
        Integer first_value = 42;
        trie.put(first_key, first_value);

        String second_key = "b";
        Integer second_value = 66;
        trie.put(second_key, second_value);

        String third_key = "car";
        Integer third_value = 11;
        trie.put(third_key, third_value);

        assertEquals(first_value, trie.get(first_key));
        assertEquals(second_value, trie.get(second_key));
        assertEquals(third_value, trie.get(third_key));
    }

    @Test
    public void testLongestPrefix(){

        MyTernarySearchTrie<Integer> trie = new MyTernarySearchTrie<>();

        trie.put("car", 0);
        trie.put("cartel", 0);
        trie.put("carry", 0);
        trie.put("a car", 0);
        trie.put("b car", 0);
        trie.put("bar", 0);
        trie.put("man", 0);

        String result = trie.longestPrefixOf("cartman");
        assertEquals("car", result);

        result = trie.longestPrefixOf("cartel");
        assertEquals("cartel", result);

        result = trie.longestPrefixOf("area");
        assertTrue(result.isEmpty(), "Non-empty result: " + result);
    }


    @Test
    public void testGetEntries(){

        MyTernarySearchTrie<Integer> trie = new MyTernarySearchTrie<>();

        trie.put("car", 0);
        trie.put("cars", 1);
        trie.put("cartman", 2);
        trie.put("cartel", 3);
        trie.put("carry", 4);
        trie.put("a car", 5);
        trie.put("b car", 6);
        trie.put("bar", 7);
        trie.put("man", 8);

        assertEquals(9, trie.entrySet().size());

        assertTrue(trie.entrySet().stream().anyMatch(p ->
                p.getKey().equals("car") && p.getValue().equals(0)));
        assertTrue(trie.entrySet().stream().anyMatch(p ->
                p.getKey().equals("cars") && p.getValue().equals(1)));
        assertTrue(trie.entrySet().stream().anyMatch(p ->
                p.getKey().equals("cartman") && p.getValue().equals(2)));
        assertTrue(trie.entrySet().stream().anyMatch(p ->
                p.getKey().equals("cartel") && p.getValue().equals(3)));
        assertTrue(trie.entrySet().stream().anyMatch(p ->
                p.getKey().equals("carry") && p.getValue().equals(4)));
        assertTrue(trie.entrySet().stream().anyMatch(p ->
                p.getKey().equals("a car") && p.getValue().equals(5)));
        assertTrue(trie.entrySet().stream().anyMatch(p ->
                p.getKey().equals("b car") && p.getValue().equals(6)));
        assertTrue(trie.entrySet().stream().anyMatch(p ->
                p.getKey().equals("bar") && p.getValue().equals(7)));
        assertTrue(trie.entrySet().stream().anyMatch(p ->
                p.getKey().equals("man") && p.getValue().equals(8)));
    }
}