package pzubaha.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Chapter_005. Collection. Pro.
 * Map
 * <p>
 * Contains solution of task 1005.
 * Class test different
 * Created 21.11.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class UserMapTest {
    private Map<User, Object> map;
    private Calendar birthday = new GregorianCalendar(1960, 3, 21);

    /**
     * Test behavior with not overridden equals, hashCode methods.
     */
    @Test
    public void whenAddUserWithNotOverriddenEquals() {
        map = new HashMap<>();
        String name = String.valueOf("User");
        User user1 = new User(name, 1, birthday);
        User user2 = new User(name, 1, birthday);
        map.put(user1, 1);
        map.put(user2, 2);
        System.out.println(map);
    }
}