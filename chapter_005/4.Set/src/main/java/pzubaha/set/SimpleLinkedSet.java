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
     * Cursor for next, has next method.
     */
    private int cursor = 0;
    /**
     * First element.
     */
    private Node<E> first;

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
     * Method for add elements.
     * @param e element that needs add.
     * @return true if element has added, otherwise false.
     */
    @Override
    public final boolean add(E e) {
        boolean result = true;
        if (e != null) {
            Node<E> addNode = new Node<>(e);
            Node<E> pointer = first;
            if (pointer != null) {
                if (first.val.equals(e)) {
                    result = false;
                }
                while (pointer.next != null) {
                    if (pointer.val.equals(e)) {
                        result = false;
                        break;
                    }
                    pointer = pointer.next;
                }
                if (result) {
                    pointer.next = addNode;
                    length++;
                }
            } else {
                first = addNode;
                length++;
            }
        } else {
            result = false;
        }
        return result;
    }

    /**
     * Method for checking has this set more elements.
     * @return true if there are present more elements, otherwise false.
     */
    @Override
    public final boolean hasNext() {
        return cursor < length;
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
        Node<E> node = first;
        for (int i = 0; i < cursor; i++) {
            node = node.next;
        }
        E result = node.val;
        cursor++;
        return result;
    }

    /**
     * Method reset current cursor for next/hasNext methods.
     */
    public void resetNext() {
        cursor = 0;
    }

    /**
     * Ger length of this set.
     * @return length.
     */
    public int length() {
        return length;
    }
}
