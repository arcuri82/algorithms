package org.pg4200.ex07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pg4200.ex06.Author;
import org.pg4200.ex06.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arcuri82 on 02-May-18.
 */
public  abstract class ComputationExampleTestTemplate {


    protected abstract ComputationExample getNewInstance();

    private ComputationExample example;


    @BeforeEach
    public void init(){
        example = getNewInstance();
    }


    @Test
    public void testEmpty(){

        List<String> list = example.compute(new ArrayList<>());

        assertEquals(0, list.size());
    }

    @Test
    public void testSingle(){

        Author first = new Author("a","b");
        Author second = new Author("c","d");

        Book book = new Book("title", 2012, Arrays.asList(first, second));

        List<String> list = example.compute(Arrays.asList(book));

        assertEquals(2, list.size());
        assertTrue(list.contains("a b"));
        assertTrue(list.contains("c d"));
    }

    @Test
    public void testMultipleWithInvalid(){

        Author first = new Author("a","b");
        Author second = new Author("c","d");

        Book ok = new Book("title", 2012, Arrays.asList(first, second));
        Book tooEarly = new Book("title", 1998, Arrays.asList(first, second));
        Book tooLate = new Book("title", 2018, Arrays.asList(first, second));
        Book onlyOneAuthor = new Book("title", 2012, Arrays.asList(first));


        List<String> list = example.compute(Arrays.asList(tooEarly, ok, tooLate, onlyOneAuthor));

        assertEquals(2, list.size());
        assertTrue(list.contains("a b"));
        assertTrue(list.contains("c d"));
    }


    @Test
    public void testMultipleOk(){

        Author first = new Author("a","b");
        Author second = new Author("c","d");
        Author third = new Author("e","f");
        Author fourth = new Author("g","h");

        Book a = new Book("title", 2012, Arrays.asList(first, second));
        Book b = new Book("title", 2013, Arrays.asList(third, fourth));


        List<String> list = example.compute(Arrays.asList(a,b));

        assertEquals(4, list.size());
        assertTrue(list.contains("a b"));
        assertTrue(list.contains("c d"));
        assertTrue(list.contains("e f"));
        assertTrue(list.contains("g h"));
    }

    @Test
    public void testInvalidAuthor(){

        Author first = new Author(null,"b");
        Author second = new Author("c","d");
        Author third = new Author("e",null);
        Author fourth = new Author("g","h");

        Book a = new Book("title", 2012, Arrays.asList(first, second));
        Book b = new Book("title", 2013, Arrays.asList(third, fourth));

        List<String> list = example.compute(Arrays.asList(a,b));

        assertEquals(2, list.size());
        assertTrue(list.contains("c d"));
        assertTrue(list.contains("g h"));
    }

    @Test
    public void testMultipleRepeated(){

        Author first = new Author("a","b");
        Author second = new Author("c","d");
        Author third = new Author("e","f");

        Book a = new Book("title", 2012, Arrays.asList(first, second));
        Book b = new Book("title", 2013, Arrays.asList(third, first));
        Book c = new Book("title", 2014, Arrays.asList(third, second));
        Book d = new Book("title", 2012, Arrays.asList(first, second, third));

        List<String> list = example.compute(Arrays.asList(a,b,c,d));

        assertEquals(3, list.size());
        assertTrue(list.contains("a b"));
        assertTrue(list.contains("c d"));
        assertTrue(list.contains("e f"));
    }
}