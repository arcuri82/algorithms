package org.pg4200.ex06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arcuri82 on 02-May-18.
 */
public abstract class ImmutableBookAndAuthorTestTemplate {

    protected abstract ImmutableBook getNewEmptyInstanceOfBook();

    protected abstract ImmutableAuthor getNewEmptyInstanceOfAuthor();


    @Test
    public void testModifyAuthor() {

        String n = "a name";
        String s = "a surname";

        ImmutableAuthor author = getNewEmptyInstanceOfAuthor()
                .withName(n)
                .withSurname(s);

        assertEquals(n, author.getName());
        assertEquals(s, author.getSurname());

        String foo = "foo";
        ImmutableAuthor copy = author.withName(foo).withSurname("bar");
        assertEquals(foo, copy.getName());

        //not modified
        assertEquals(n, author.getName());
        assertEquals(s, author.getSurname());
    }

    @Test
    public void testModifyBook(){

        String title = "Algorithms";
        int year = 2018;

        ImmutableBook book = getNewEmptyInstanceOfBook()
                .withTitle(title)
                .withYear(year);

        assertEquals(title, book.getTitle());
        assertEquals(year, book.getYear());


        String foo = "foo";
        ImmutableBook copy = book.withTitle(foo).withYear(0);
        assertEquals(foo, copy.getTitle());

        //not modified
        assertEquals(title, book.getTitle());
        assertEquals(year, book.getYear());
    }

    @Test
    public void testModifyAuthorsInBook(){

        String foo = "foo";
        String bar = "bar";

        ImmutableAuthor first = getNewEmptyInstanceOfAuthor().withName(foo);
        ImmutableAuthor second = getNewEmptyInstanceOfAuthor().withName(bar);

        ImmutableBook book = getNewEmptyInstanceOfBook()
                .withAuthors(first, second);

        assertEquals(2, book.getAuthors().size());

        try{
            book.getAuthors().clear();
        }catch (Exception e){
            //might or might not happen
        }

        assertEquals(2, book.getAuthors().size());
    }
}