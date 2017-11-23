package pzubaha.map;

import java.util.Calendar;

/**
 * Chapter_005. Collection. Pro.
 * 5.Map.
 * User with overridden equals.
 * <p>
 * Contains solution of task 1004.
 * Created 23.11.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class UserEqualsOverridden extends User {
    /**
     * Constructor.
     * @param name     User name.
     * @param children amount of children.
     * @param birthday user birthday.
     */
    public UserEqualsOverridden(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    /**
     * Call Object.equals for checkstyle passing.
     * @return boolean value.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
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
