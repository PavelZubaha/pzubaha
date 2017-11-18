package pzubaha.set;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.core.Is.is;

/**
 * Chapter_005. Collection. Pro.
 * 4.Set.
 * Task 996.
 * Test of SimpleArraySet.
 * Created 16.11.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class SimpleArraySetTest {
    /**
     * Tested SimpleArraySet reference.
     */
    private SimpleArraySet<Integer> as;
    /**
     * Set up before test.
     */
    @Before
    public void setUp() {
        as = new SimpleArraySet<>();
    }

    /**
     * Test add method.
     */
    @Test
    public void whenAddNotAlreadyPresentElementThenItWasAddedAndTrueReturned() {
        assertTrue(as.add(1));
        assertTrue(!as.add(1));
        assertThat(1, is(as.amount()));
        assertTrue(as.add(0));
        assertTrue(!as.add(0));
        assertThat(2, is(as.amount()));
        assertTrue(as.add(3));
    }

    /**
     * Test iterator behavior.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenTryHasNextThenIteratorReturnTrueIfThereElementIsAlreadyPresent() {
        assertTrue(!as.hasNext());
        assertTrue(as.add(0));
        assertTrue(as.hasNext());
        assertThat(as.next(), is(0));
        assertTrue(as.add(1));
        assertTrue(as.hasNext());
        assertThat(as.next(), is(1));
        assertTrue(!as.hasNext());
        as.next();
    }

    /**
     * Test order of stored sequence.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenASequenceOfUniqueElementsIsAddedThenTheyArePresentInAscendingOrderByHashCode() {
        assertTrue(as.add(3));
        assertTrue(as.add(2));
        assertTrue(!as.add(2));
        assertTrue(as.add(0));
        assertTrue(as.add(1));
        assertThat(as.next(), is(0));
        assertThat(as.next(), is(1));
        assertThat(as.next(), is(2));
        assertThat(as.next(), is(3));
        as.next();
    }
}