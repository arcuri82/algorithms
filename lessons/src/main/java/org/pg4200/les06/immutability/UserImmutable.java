package org.pg4200.les06.immutability;

/**
 * If for any reason you use an object in a set, or as a key in a map,
 * it MUST be IMMUTABLE.
 *
 * Note: String and Integer are immutable.
 * For example, when you call String#toLowerCase that does not change
 * the current string, but rather create a new one with all chars in lower case.
 *
 * Created by arcuri82 on 14-Sep-17.
 */
public class UserImmutable {

    private final String name;
    private final String surname;
    private final int id;


    public UserImmutable(String name, String surname, int id) {
        this.name = name;
        this.surname = surname;
        this.id = id;
    }

    /*
        Instead of changing the values of an object, we create
        a new copy with that new value overriding the current one
     */

    public UserImmutable withName(String s){
        return new UserImmutable(s, surname, id);
    }

    public UserImmutable withSurname(String s){
        return new UserImmutable(name, s, id);
    }

    public UserImmutable withId(int x){
        return new UserImmutable(name, surname, x);
    }

    /*
        We can have getters, but no setters, as all fields are final
     */

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserImmutable that = (UserImmutable) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return surname != null ? surname.equals(that.surname) : that.surname == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
