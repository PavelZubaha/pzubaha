package pzubaha.set;

import org.hamcrest.core.Is;
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
 * Test of Array Set.
 * Created 16.11.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class ArraySetTest {
    /**
     * Tested ArraySet reference.
     */
    private ArraySet<Integer> as;
    /**
     * Set up before test.
     */
    @Before
    public void setUp() {
        as = new ArraySet<>();
    }

    /**
     * Test add method.
     */
    @Test
    public void whenAddNotAlreadyPresentElementThenItWasAddedAndTrueReturned() {
        assertThat(0, Is.is(as.amount()));
        assertTrue(as.add(1));
        assertTrue(!as.add(1));
        assertThat(1, Is.is(as.amount()));
        assertTrue(as.add(0));
        assertTrue(!as.add(0));
        assertThat(2, Is.is(as.amount()));
    }

    /**
     * Test remove method.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenRemovePresentElementByValueThenElementDeletedAndReturnTrue() {
        as.add(0);
        as.add(1);
        as.add(2);
        as.add(3);
        assertTrue(!as.remove(10));
        assertTrue(as.remove(3));
        assertTrue(as.remove(2));
        assertTrue(as.remove(1));
        assertTrue(as.remove(0));
        as.next();
    }
}