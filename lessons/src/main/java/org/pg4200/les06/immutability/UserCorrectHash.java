package org.pg4200.les06.immutability;

/**
 * Created by arcuri82 on 14-Sep-17.
 */
public class UserCorrectHash extends UserWrongHash {

    public UserCorrectHash(String name, String surname, int id) {
        super(name, surname, id);
    }

    /*
        As "equals()" is based only on the state of "id", then we
        must do the same as well for hashCode
     */

    @Override
    public int hashCode() {
        return getId();
    }
}
