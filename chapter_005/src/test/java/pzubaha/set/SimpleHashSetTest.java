package pzubaha.set;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Chapter_005. Collection. Pro.
 * 4.Set.
 * Test SimpleHashSet class.
 * <p>
 * Contains solution of task 998.
 * Based on hash table.
 * Created 20.11.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class SimpleHashSetTest {
    private SimpleHashSet<String> simpleHashSet;
    @Before
    public void setUp() {
        simpleHashSet = new SimpleHashSet<>(16);
    }

    /**
     * Test add method.
     */
    @Test
    public void whenAddElementToHashSetThenRetursTrueIfElementDoesNotPresentElseFalse() {
        assertTrue(simpleHashSet.add("1"));
        assertTrue(!simpleHashSet.add("1"));
        assertTrue(simpleHashSet.add("2"));
        assertTrue(simpleHashSet.contains("1"));
        assertThat(simpleHashSet.getAmount(), is(2));
    }

    /**
     * Test remove method.
     */
    @Test
    public void whenRemoveElementThenReturnsTrueIfTheElementHadRemovedElseFalse() {
        assertTrue(simpleHashSet.add("1"));
        assertTrue(simpleHashSet.add("2"));
        assertTrue(simpleHashSet.add("3"));
        assertThat(simpleHashSet.getAmount(), is(3));
        assertTrue(simpleHashSet.remove("2"));
        assertTrue(simpleHashSet.remove("1"));
        assertTrue(simpleHashSet.remove("3"));
        assertTrue(!simpleHashSet.remove("1"));
        assertTrue(!simpleHashSet.remove("2"));
    }
}