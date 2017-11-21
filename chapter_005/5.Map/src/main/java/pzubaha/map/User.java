package pzubaha.map;

import java.util.Calendar;

/**
 * Chapter_005. Collection. Pro.
 * 5.Map
 * <p>
 * Contains solution of task 999.
 * Class represents the model of the User.
 * Created 21.11.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class User {
    /**
     * User name.
     */
    private String name;
    /**
     * Number of user's children
     */
    private int children;
    /**
     * Birthday of user.
     */
    private Calendar birthday;

    /**
     * Constructor.
     * @param name User name.
     * @param children amount of children.
     * @param birthday user birthday.
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }


}
