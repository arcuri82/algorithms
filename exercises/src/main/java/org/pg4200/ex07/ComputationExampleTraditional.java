package org.pg4200.ex07;

import org.pg4200.ex06.Author;
import org.pg4200.ex06.Book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by arcuri82 on 02-May-18.
 */
public class ComputationExampleTraditional implements ComputationExample{


    @Override
    public List<String> compute(List<Book> books) {

        Set<String> results = new HashSet<>();

        for(Book book : books){
            int year = book.getYear();
            if(year < 2010 || year > 2015){
                continue;
            }

            List<Author> authors = book.getAuthors();
            if(authors.size() < 2){
                continue;
            }

            for(Author author : authors){
                if(author.getName() == null || author.getSurname()==null){
                    continue;
                }

                String value = author.getName() + " " + author.getSurname();
                results.add(value);
            }
        }

        return new ArrayList<>(results);
    }
}
