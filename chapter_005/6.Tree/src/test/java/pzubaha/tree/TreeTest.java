package pzubaha.tree;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Chapter_005. Collection. Pro.
 * 6.Tree. Elementary tree.
 * <p>
 * Contains solution of task 1711.
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
}
