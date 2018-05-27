package pzubaha.userconvert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Chapter_003. Collections. Lite.
 * Generic.
 * <p>
 * Contains solution of task 10093.
 * Test class for UserConvert.
 * Created 27.09.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */

public class UserConvertTest {

    /**
     * test method for UserConvert.process() method.
     */
    @Test
    public void whenConvertUserListThenMapHasAllOriginalUsers() {
        UserConvert userConvert = new UserConvert();

        //define original users, list
        User userJapan = new User("Akihito", "Tokyo", 1);
        User userHindu = new User("Indira Gandhi", "Delhi", 2);
        List<User> usersList = new ArrayList<>();
        //add users to userList
        usersList.add(userJapan);
        usersList.add(userHindu);

        //define expected map
        Map<Integer, User> expectedMap = new HashMap<>();
        expectedMap.put(userJapan.getiD(), userJapan);
        expectedMap.put(userHindu.getiD(), userHindu);

        //check
        assertThat(userConvert.process(usersList), is(expectedMap));
    }
}
