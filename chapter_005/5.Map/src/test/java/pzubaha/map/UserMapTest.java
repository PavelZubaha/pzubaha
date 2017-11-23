package pzubaha.map;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Chapter_005. Collection. Pro.
 * 5.Map
 * <p>
 * Contains solution of task 1005, 1003.
 * Class test for representing results of putting on HashMap
 * different User classes.
 * Created 21.11.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class UserMapTest {
    private Map<User, Object> map;
    private Calendar birthday = new GregorianCalendar(1960, 3, 21);
    private String name = String.valueOf("User");
    @Before
    public void setUpBefore() {
        map = new HashMap<>();
    }

    /**
     * Test behavior with not overridden equals, hashCode methods.
     */
    @Test
    public void whenAddUserWithNotOverriddenEquals() {
        User user1 = new User(name, 1, birthday);
        User user2 = new User(name, 1, birthday);
        map.put(user1, 1);
        map.put(user2, 2);
        System.out.println(map);
    }
    /**
     * Test behavior with overridden equals, hashCode methods.
     */
    @Test
    public void whenAddUserWithOverriddenHashCodeAndEqualsNotOverride() {
        String name = String.valueOf("User");
        UserHashCodeOverridden user1 = new UserHashCodeOverridden(name, 1, birthday);
        UserHashCodeOverridden user2 = new UserHashCodeOverridden(name, 1, birthday);
        map.put(user1, 1);
        map.put(user2, 2);
        System.out.println(map);
    }
}