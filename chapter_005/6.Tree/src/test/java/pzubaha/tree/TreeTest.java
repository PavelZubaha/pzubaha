package pzubaha.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Chapter_005. Collection. Pro.
 * 6.Tree. Elementary tree.
 * <p>
 * Contains solution of task 1712.
 * Class represents tests for Tree class.
 * Created 26.12.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 * <T> - parametrized type implementing Comparable.
 */
public class TreeTest {
    private Tree<Integer> tree;
    @Before
    public void setUp() {
        tree = new Tree<>(1);
    }

    /**
     * Test add method.
     * When element parent not find or child present in tree should returns false.
     */
    @Test
    public void when6ElFindLastThen6() {
        tree.add(1, 2);
        assertThat(tree.add(1, 2), is(false));
        tree.add(1, 3);
        assertThat(tree.add(10, 2), is(false));
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    /**
     * Test isPresent method.
     */
    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }
    /**
     * Test isBinary method.
     */
    @Test
    public void whenAddMoreTwoChildToParentThanTreeBecomeNotBinary() {
        assertThat(tree.isBinary(), is(true));
        tree.add(1, 2);
        assertThat(tree.isBinary(), is(true));
        tree.add(1, 3);
        assertThat(tree.isBinary(), is(true));
        tree.add(1, 4);
        assertThat(tree.isBinary(), is(false));
    }

    /**
     * Test returned iterator.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenHasMoreElementsThanIteratorReturnTrue() {
        tree.add(1, 2);
        tree.add(2, 3);
        tree.add(3, 4);
        Iterator<Integer> it = tree.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(false));
        it.next();
    }
}
