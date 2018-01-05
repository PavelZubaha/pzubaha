package pzubaha.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Chapter_005. Collection. Pro.
 * 6.Tree. Binary search tree(BST).
 * <p>
 * Contains solution of task 1714.
 * Class represents simple BST.
 * This class can store not nullable objects.
 * Created 05.01.2018.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 * <T> - parametrized type implementing Comparable.
 */
public class BinarySearchTree<T extends Comparable<T>> implements Iterable<T> {
    private final T value;
    private BinarySearchTree<T> left;
    private BinarySearchTree<T> rigth;

    public BinarySearchTree(T value) {
        if (value == null) {
            throw new NoSuchElementException("Nullable elements is not allowed");
        }
        this.value = value;
    }

    /**
     * Simple getter for value.
     * @return value.
     */
    public T getValue() {
        return value;
    }

    /**
     * Add element.
     * Recursive implementation.
     * @param element specified element to add.
     * @return true in case when element has added, otherwise false.
     */
    public boolean add(T element) {
        boolean result = false;
        if (element != null) {
            BinarySearchTree<T> treeToAdd = findBy(element);
            int compare = treeToAdd.value.compareTo(element);
            if (compare < 0) {
                treeToAdd.rigth = new BinarySearchTree<>(element);
                result = true;
            } else if (compare > 0) {
                treeToAdd.left = new BinarySearchTree<>(element);
                result = true;
            }
        }
        return result;
    }

    /**
     * Checking contains the specified element in the tree or not.
     * @param element element for check.
     * @return true if the tree contains specified element, otherwise false.
     */
    public boolean contains(T element) {
        return findBy(element).value.equals(element);
    }

    /**
     * Finds BST element by value where specified element should be placed.
     * @param element element that needs to found.
     * @return BinarySearchTree where element might stored.
     */
    private BinarySearchTree<T> findBy(T element) {
        BinarySearchTree<T> result = this;
        int compare = value.compareTo(element);
        if (compare < 0) {
            //right
            if (rigth != null) {
                result = rigth.findBy(element);
            }
            // if right == null ==> result = this
        } else if (compare > 0) {
            //left
            if (left != null) {
                result = left.findBy(element);
            }
            // if left == null ==> result = this
        }
        //if compare == 0 ==> result = this.
        return result;
    }

    /**
     * Get iterator for this tree.
     * The iterator iterate elements by breadth-first order.
     * @return iterator of this BST.
     */
    public Iterator<T> iterator() {
        class BSTIterator implements Iterator<T> {
            private Queue<BinarySearchTree<T>> nextData = new LinkedList<>();
            private BSTIterator() {
                nextData.offer(BinarySearchTree.this);
            }
            @Override
            public boolean hasNext() {
                return !nextData.isEmpty();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                BinarySearchTree<T> result = nextData.poll();
                if (result.left != null) {
                    nextData.offer(result.left);
                }
                if (result.rigth != null) {
                    nextData.offer(result.rigth);
                }
                return result.value;
            }
        }
        return new BSTIterator();
    }
}
