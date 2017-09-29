package pzubaha.comparable;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Chapter_003. Collections. Lite.
 * Sort.
 * <p>
 * Contains solution of task 10034.
 * Test class for UserSort.
 * Created 29.09.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class UserSortTest {

    /**
     * Test for sort method.
     */
    @Test
    public void whenUseSortUserListThenReturnedSortedSetOfOriginalUsers() {

        //Create tested instance
        UserSort us = new UserSort();

        //assign original List of User's
        User user1 = new User("Cris", 35);
        User user2 = new User("Bob", 30);
        User user3 = new User("Ann", 25);
        User user4 = new User("Key", 40);
        User user5 = new User("Olga", 33);
        User user6 = new User("Yan", 28);

        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(user1, user2, user3, user4, user5, user6));

        //assign expected String
        String expected = String.format("[%s, %s, %s, %s, %s, %s]", user3, user6, user2, user5, user1, user4);

        //check result of pzubaha.comparable.User.sort(list)
        assertThat(us.sort(list).toString(), is(expected));
    }
}
