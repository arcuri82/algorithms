package org.pg4200.sol06;

import org.pg4200.ex06.ImmutableAuthor;
import org.pg4200.ex06.ImmutableBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by arcuri82 on 02-May-18.
 */
public class ImmutableBookImp implements ImmutableBook {

    private final String title;

    private final int year;

    private final List<ImmutableAuthor> authors;

    public ImmutableBookImp() {
        this(null, -1, null);
    }

    public ImmutableBookImp(String title, int year, List<ImmutableAuthor> authors) {
        this.title = title;
        this.year = year;
        this.authors = authors == null ? null : Collections.unmodifiableList(authors);
    }

    @Override
    public ImmutableBook withTitle(String title) {
        return new ImmutableBookImp(title, this.year, this.authors);
    }

    @Override
    public ImmutableBook withYear(int year) {
        return new ImmutableBookImp(this.title, year, this.authors);
    }

    @Override
    public ImmutableBook withAuthors(List<ImmutableAuthor> authors) {
        return new ImmutableBookImp(this.title, this.year, authors);
    }

    @Override
    public ImmutableBook withAuthors(ImmutableAuthor... authors) {
        return withAuthors(Arrays.asList(authors));
    }


    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public List<ImmutableAuthor> getAuthors() {
        return authors;
    }
}
