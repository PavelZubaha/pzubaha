package pzubaha.tree;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Chapter_005. Collection. Pro.
 * 6.Tree. Binary search tree (BST).
 * <p>
 * Contains solution of task 1714.
 * Class represents tests for simple binary search tree.
 * This class can store not nullable objects.
 * Created 26.12.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 * <T> - parametrized type implementing Comparable.
 */
public class BinarySearchTreeTest {
    @Test
    public void whenAddElementThanThatContainsInTheTree() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(10);
        assertThat(bst.contains(1), is(false));
        assertThat(bst.contains(10), is(true));
        assertThat(bst.contains(11), is(false));
        assertThat(bst.add(11), is(true));
        assertThat(bst.contains(11), is(true));
        assertThat(bst.add(20), is(true));
        assertThat(bst.contains(20), is(true));
        assertThat(bst.contains(15), is(false));
        assertThat(bst.add(15), is(true));
        assertThat(bst.contains(15), is(true));
    }
}