package org.pg4200.sol06;

import org.pg4200.ex06.ImmutableAuthor;

/**
 * Created by arcuri82 on 02-May-18.
 */
public class ImmutableAuthorImp implements ImmutableAuthor {

    private final String name;

    private final String surname;

    public ImmutableAuthorImp() {
        this(null, null);
    }

    public ImmutableAuthorImp(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public ImmutableAuthor withName(String name) {
        return new ImmutableAuthorImp(name, this.surname);
    }

    @Override
    public ImmutableAuthor withSurname(String surname) {
        return new ImmutableAuthorImp(this.name, surname);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }
}
