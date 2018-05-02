package org.pg4200.sol06;

import org.pg4200.ex06.ImmutableAuthor;
import org.pg4200.ex06.ImmutableBook;
import org.pg4200.ex06.ImmutableBookAndAuthorTestTemplate;

/**
 * Created by arcuri82 on 02-May-18.
 */
public class ImmutableBookAndAuthorTest extends ImmutableBookAndAuthorTestTemplate {

    @Override
    protected ImmutableBook getNewEmptyInstanceOfBook() {
        return new ImmutableBookImp();
    }

    @Override
    protected ImmutableAuthor getNewEmptyInstanceOfAuthor() {
        return new ImmutableAuthorImp();
    }
}
