package org.pg4200.ex06;

import java.util.List;
import java.util.Objects;

/**
 * Created by arcuri82 on 02-May-18.
 */
public class Book {

    private String title;

    private int year;

    private List<Author> authors;

    public Book(){
    }

    public Book(String title, int year, List<Author> authors) {
        this.title = title;
        this.year = year;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return year == book.year &&
                Objects.equals(title, book.title) &&
                Objects.equals(authors, book.authors);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, year, authors);
    }
}
