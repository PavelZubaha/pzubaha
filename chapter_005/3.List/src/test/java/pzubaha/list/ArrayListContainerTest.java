package pzubaha.list;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
/**
 * Chapter_005. Collection. Pro.
 * List.
 * <p>
 * Contains test of solution of task 158.
 * Created 05.11.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class ArrayListContainerTest {
    /**
     * Class for testing container.
     */
    private class A {
    }

    /**
     * First test instance.
     */
    private A testValue1 = new A();
    /**
     * Second test instance.
     */
    private A testValue2 = new A();
    /**
     * Reference to test.
     */
    private ArrayListContainer<A> aArrayListContainer;

    /**
     * set up before testing.
     */
    @Before
    public void setUp() {
        aArrayListContainer = new ArrayListContainer<A>(15);
        aArrayListContainer.add(testValue1);
        aArrayListContainer.add(testValue2);
    }

    /**
     * test add and get method.
     */
    @Test(expected = java.util.NoSuchElementException.class)
    public void whenAddElementThenElementCouldBeGot() {
        assertThat(aArrayListContainer.get(0), is(testValue1));
        assertThat(aArrayListContainer.get(1), is(testValue2));
        aArrayListContainer.get(2);
    }

    /**
     * Test Iterator of ArrayListContainer.
     */
    @Test(expected = java.util.NoSuchElementException.class)
    public void whenThereIsNextElementIteratorHasNextReturnsTrueOtherwiseFalse() {
        Iterator<A> it = aArrayListContainer.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is(testValue1));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(testValue2));
        assertTrue(!it.hasNext());
        it.next();
    }
}
