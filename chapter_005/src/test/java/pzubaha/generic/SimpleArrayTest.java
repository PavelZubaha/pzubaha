package pzubaha.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Chapter_005. Collection. Pro.
 * Generic.
 * <p>
 * Contains solution of task 156.
 * Class for simple array implementation.
 * Created 24.10.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class SimpleArrayTest<T> {
    /**
     * tested instance;
     */
    private SimpleArray<String> sa;
    @Before
    public void setUp() {
        sa = new SimpleArray<>(3);
        sa.add(String.valueOf("Test 1"));
        sa.add(String.valueOf("Test 2"));
        sa.add(String.valueOf("Test 3"));
    }

    /**
     * Test for add and get methods.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenItemsAraAddedThenTheyCanGotFromSimpleArray() {
        assertThat(sa.get(0), is("Test 1"));
        assertThat(sa.get(1), is("Test 2"));
        assertThat(sa.get(2), is("Test 3"));
        sa.get(3);
    }

    /**
     * Test for update method.
     */
    @Test
    public void whenUpdateExitingElementThenReturnedTrueOtherwiseFalse() {
        String expected = String.valueOf("Updated Test");
        assertThat(sa.update(2, expected), is(true));
        assertThat(sa.get(2), is(expected));
        assertThat(sa.update(3, expected), is(false));
    }

    /**
     * Test for delete method.
     */
    @Test
    public void whenDeleteByTheIndexThenTheElementRemovedAndReturnedTrueOtherwiseFalse() {
        String expected = sa.get(2);
        assertThat(sa.delete(0), is(true));
        assertThat(sa.delete(0), is(true));
        assertThat(sa.delete(1), is(false));
        assertThat(expected, is(sa.get(0)));
    }
}
