package pzubaha.map;

import java.util.Calendar;

/**
 * Chapter_005. Collection. Pro.
 * 5.Map.
 * User with overridden hash code.
 * <p>
 * Contains solution of task 1003.
 * Created 23.11.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class UserHashCodeOverridden extends User {
    /**
     * Constructor for User.
     * @param name name of User.
     * @param children amount of children.
     * @param birthday birthday.
     */
    public UserHashCodeOverridden(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    /**
     * Calculating hash code.
     * @return hash code of this User.
     */
    @Override
    public int hashCode() {
        int hash = 31 + getName().hashCode();
        hash = hash * 31 + getChildren();
        hash = hash * 31 + (int) (getBirthday().getTimeInMillis() >> 32);
        return hash;
    }
}