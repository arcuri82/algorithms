package org.pg4200.ex06;

import java.util.List;

/**
 * Created by arcuri82 on 02-May-18.
 */
public interface ImmutableBook {

    /**
     * @return a copy of this Book, but with different title
     */
    ImmutableBook withTitle(String title);

    /**
     * @return a copy of this Book, but with different year
     */
    ImmutableBook  withYear(int year);

    /**
     * @return a copy of this Book, but with different authors
     */
    ImmutableBook withAuthors(List<ImmutableAuthor> authors);

    /**
     * @return a copy of this Book, but with different authors
     */
    ImmutableBook withAuthors(ImmutableAuthor... authors);

    String getTitle();

    int getYear();

    List<ImmutableAuthor> getAuthors();
}
