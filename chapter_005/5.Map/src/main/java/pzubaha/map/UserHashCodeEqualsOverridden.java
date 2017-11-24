package pzubaha.map;

import java.util.Calendar;

/**
 * Chapter_005. Collection. Pro.
 * 5.Map.
 * User with overridden equals, hashCode methods.
 * Class made for showing different HashMap behavior with/without
 * overriding equals, hashCode methods.
 * <p>
 * Contains solution of task 1002.
 * Created 23.11.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class UserHashCodeEqualsOverridden extends UserHashCodeOverridden {

    /**
     * Constructor.
     * @param name user name.
     * @param children amount of User children.
     * @param birthday user birthday.
     */
    public UserHashCodeEqualsOverridden(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    /**
     * Method for comparing equals.
     * @param obj another object for checking.
     * @return true - if this user equals another, otherwise - false.
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj == this) {
            result = true;
        } else if (obj != null && obj.getClass() == this.getClass()) {
            User another = (User) obj;
            result = this.getName().equals(another.getName())
                    && this.getChildren() == another.getChildren()
                    && this.getBirthday().equals(another.getBirthday());
        }
        return result;
    }
}
