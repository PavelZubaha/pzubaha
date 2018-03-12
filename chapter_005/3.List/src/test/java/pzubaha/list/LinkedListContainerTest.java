package pzubaha.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Chapter_005. Collection. Pro.
 * List.
 * <p>
 * Contains solution of task 159.
 * Test class for LinkedListContainer.
 * Created 07.11.2017.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class LinkedListContainerTest {
    /**
     * tested variables.
     */
    private LinkedListContainer<String> testedLLC;
    //String instances for testing.
    private String first = String.valueOf("first");
    private String second = String.valueOf("second");
    private String third = String.valueOf("third");
    private String zero = String.valueOf("zero");
    private String insert = String.valueOf("insert");
    /**
     * Set up before test methods.
     */
    @Before
    public void setUp() {
        testedLLC = new LinkedListContainer<>();
        testedLLC.add(first);
        testedLLC.add(second);
        testedLLC.add(third);
    }
    /**
     * Test add and get methods.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenAddElementThenElementCouldBeReturnedByGetMethod() {
        assertThat(testedLLC.get(0), is(first));
        assertThat(testedLLC.get(1), is(second));
        assertThat(testedLLC.get(2), is(third));
        testedLLC.get(3);
    }
    /**
     * Test add by index and get methods.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenAddElementInsertThenElementCouldBeReturnedByGetMethod() {
        testedLLC.add(zero, 0);
        testedLLC.add(insert, 2);
        assertThat(testedLLC.get(0), is(zero));
        assertThat(testedLLC.get(1), is(first));
        assertThat(testedLLC.get(2), is(insert));
        assertThat(testedLLC.get(3), is(second));
        assertThat(testedLLC.get(4), is(third));
        testedLLC.get(5);
    }
    /**
     * Test iterator.
     */
    @Test(expected = NoSuchElementException.class)
    public void checkIteratorBehavior() {
        Iterator<String> it = testedLLC.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is(first));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(second));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(third));
        it.next();
    }
    /**
     * Test iterator fail-fast.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void checkIteratorFailFastBehavior() {
        Iterator<String> it = testedLLC.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is(first));
        testedLLC.remove(2);
        it.next();
    }
}