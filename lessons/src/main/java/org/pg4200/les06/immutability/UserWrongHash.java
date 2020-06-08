package org.pg4200.les06.immutability;

/**
 * Created by arcuri82 on 14-Sep-17.
 */
public class UserWrongHash extends User{

    public UserWrongHash(String name, String surname, int id) {
        super(name, surname, id);
    }

    /*
        If A.equals(B), then it MUST happen that A.hashCode() == B.hashCode().
        If not, then it is going to be BIG problems...

        Let's say we want to change the behavior of User by looking for
        equality only if the id is the same.
        This might for example be done to save time on comparing name/surname
        if anyway the ids are unique.
        However, assume that we forget to change hashCode(), which would
        still be based on all 3 parameters... this would break the constrain above :(
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return getId() == user.getId();
    }
}
