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
 * Contains solution of task 1005, 1003, 1004.
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
     * Test behavior with overridden hashCode method.
     */
    @Test
    public void whenAddUserWithOverriddenHashCodeAndEqualsNotOverride() {
        UserHashCodeOverridden user1 = new UserHashCodeOverridden(name, 1, birthday);
        UserHashCodeOverridden user2 = new UserHashCodeOverridden(name, 1, birthday);
        map.put(user1, 1);
        map.put(user2, 2);
        System.out.println(map);
    }
    /**
     * Test behavior with overridden equals method.
     */
    @Test
    public void whenAddUserWithOverriddenEqualsAndHashCodeNotOverride() {
        UserEqualsOverridden user1 = new UserEqualsOverridden(name, 1, birthday);
        UserEqualsOverridden user2 = new UserEqualsOverridden(name, 1, birthday);
        map.put(user1, 1);
        map.put(user2, 2);
        System.out.println(map);
    }

    /**
     * Test behavior with overridden equals and hashCode methods.
     */
    @Test
    public void whenAddUserHashCodeEqualsOverride() {
        UserHashCodeEqualsOverridden us1 = new UserHashCodeEqualsOverridden(name, 1, birthday);
        UserHashCodeEqualsOverridden us2 = new UserHashCodeEqualsOverridden(name, 1, birthday);
        map.put(us1, 1);
        map.put(us2, 2);
        System.out.println(map);
    }

}