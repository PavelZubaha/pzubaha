package pzubaha.tree;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Chapter_005. Collection. Pro.
 * 6.Tree. Binary search tree (BST).
 * <p>
 * Contains solution of task 1714.
 * Class represents tests for simple binary search tree.
 * This class can store not nullable objects.
 * Created 05.01.2018.
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
    @Test(expected = NoSuchElementException.class)
    public void whenCallNextInIteratorObjThanItReturnsNextElementIfHasNext() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(10);
        bst.add(5);
        bst.add(15);
        bst.add(9);
        bst.add(8);
        Iterator<Integer> it = bst.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(10));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(15));
        assertThat(it.next(), is(9));
        assertThat(it.next(), is(8));
        it.next();
    }
}