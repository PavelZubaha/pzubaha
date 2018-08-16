package pzubaha.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Chapter_005. Collection. Pro.
 * Generic.
 * <p>
 * Contains solution of task 157
 * Test class.
 * Created 25.10.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class StoreTest {
    /**
     * Variable for RoleStore instance reference.
     */
    private RoleStore roleStore;
    /**
     * Variable for UserStore instance reference.
     */
    private UserStore userStore;

    /**
     * Variable for different tests.
     */
    private Role role1 = new Role("FirstRole");
    /**
     * Variable for different tests.
     */
    private Role role2 = new Role("SecondRole");
    /**
     * Variable for different tests.
     */
    private User user1 = new User("FirstUser");
    /**
     * Variable for different tests.
     */
    private User user2 = new User("SecondUser");
    /**
     * Set up before testing.
     */
    @Before
    public void setUp() {
        roleStore = new RoleStore();
        userStore = new UserStore();
        roleStore.add(role1);
        roleStore.add(role2);
        userStore.add(user1);
        userStore.add(user2);
    }

    /**
     * Test delete, getById methods.
     */
    @Test
    public void whenDeleteElementThenElementDoesNotContainedAtTheStoreAndTrueValueReturned() {
        assertThat((roleStore.getById("FirstRole") != null), is(true));
        assertThat(roleStore.delete("FirstRole"), is(true));
        assertThat((roleStore.getById("FirstRole") == null), is(true));

        assertThat(userStore.delete("SecondUser"), is(true));
        assertThat(userStore.delete("FirstUser"), is(true));
        assertThat(userStore.delete("SecondUser"), is(false));
    }
    /**
     * Test update method.
     */
    @Test
    public void whenUpdateElementThanNewElementStoredInstead() {
        User updatedUser = new User("updateUser");
        Role updateRole = new Role("updateRole");

        assertThat(roleStore.update(updateRole, "FirstRole"), is(true));
        assertThat(userStore.update(updatedUser, "FirstUser"), is(true));

        assertTrue(null == roleStore.getById("FirstRole"));
        assertTrue(null == userStore.getById("FirstUser"));

        assertThat(roleStore.getById(updateRole.getId()), is(updateRole));
        assertThat(userStore.getById(updatedUser.getId()), is(updatedUser));
    }
}
