package org.pg4200.ex06;

/**
 * Created by arcuri82 on 02-May-18.
 */
public interface ImmutableAuthor {

    /**
     * @return a copy of this Author, but with different name
     */
    ImmutableAuthor withName(String name);

    /**
     * @return a copy of this Author, but with different surname
     */
    ImmutableAuthor withSurname(String surname);

    String getName();

    String getSurname();
}
