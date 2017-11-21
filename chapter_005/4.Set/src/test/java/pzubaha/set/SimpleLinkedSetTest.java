package pzubaha.set;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Chapter_005. Collection. Pro.
 * 4.Set
 * Task 997.
 * <p>
 * Test SimpleLinkedListTest
 * Created 18.11.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class SimpleLinkedSetTest {
    /**
     * Tested variable.
     */
    private SimpleSet<String> simpSet;
    @Before
    public void setUp() {
        //create tested instances.
        simpSet = new SimpleLinkedSet<>();
        simpSet.add("1");
    }

    /**
     * Test add, next methods
     */
    @Test(expected = NoSuchElementException.class)
    public void whenAddNewElementThenItWillBePresentInTheSetAndTrueReturned() {
        assertTrue(simpSet.hasNext());
        assertTrue(!simpSet.add("1"));
        assertTrue(simpSet.add("0"));
        assertTrue(simpSet.add("3"));
        assertTrue(simpSet.add("2"));
        assertThat(simpSet.next(), is("1"));
        assertThat(simpSet.next(), is("0"));
        assertThat(simpSet.next(), is("3"));
        assertThat(simpSet.next(), is("2"));
        assertTrue(!simpSet.hasNext());
        simpSet.next();
    }

}