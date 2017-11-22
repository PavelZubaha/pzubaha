package pzubaha.set;

import java.util.NoSuchElementException;

/**
 * Chapter_005. Collection. Pro.
 * 4.Set
 * Task 997.
 * <p>
 * Class represents set data structure,
 * based on simple linked list.
 * Created 18.11.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class SimpleLinkedSet<E> implements SimpleSet<E> {
    /**
     * Length of element sequence.
     */
    private int length = 0;
    /**
     * First element.
     */
    private Node<E> first;
    /**
     * Last element.
     */
    private Node<E> last;
    /**
     * Cursor for next, has next method.
     */
    private Node<E> cursor = first;
    /**
     * Class for wrap and store elements.
     * @param <E> parametrized type.
     */
    private class Node<E> {
        private Node(E e) {
            this.val = e;
        }
        private E val;
        private Node<E> next;
    }
    /**
     * Adds e value to the end of singly-linked list.
     * @param e - value to add
     * @return - true - value has been added, false - otherwise.
     */
    @Override
    public final boolean add(E e) {
        if (e == null) {
            return false;
        }
        boolean result = false;
        Node<E> addNode = new Node<>(e);
        if (first == null) {
            first = addNode;
            cursor = first;
            result = true;
            length++;
            last = addNode;
        } else if (!contains(e)) {
            last.next = addNode;
            last = addNode;
            result = true;
            length++;
        }
        return result;
    }
    /**
     * Does linked list contains particular e value, or not.
     * @param e - value to search.
     * @return - true - contains, false - otherwise.
     */
    private boolean contains(E e) {
        boolean result = false;
        Node<E> pointer = first;
        while (pointer != null) {
            result = pointer.val.equals(e);
            if (result) {
                result = true;
                break;
            }
            pointer = pointer.next;
        }
        return result;
    }
    /**
     * Method for checking has this set more elements.
     * @return true if there are present more elements, otherwise false.
     */
    @Override
    public final boolean hasNext() {
        return cursor != null;
    }
    /**
     * Method returns next element of the set.
     * @return next element
     * @throws NoSuchElementException method called when no more elements in the set.
     */
    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        E result = cursor.val;
        cursor = cursor.next;
        return result;
    }
    /**
     * Method reset current cursor for next/hasNext methods.
     */
    public void resetNext() {
        cursor = first;
    }
    /**
     * Ger length of this set.
     * @return length.
     */
    public int length() {
        return length;
    }
}
