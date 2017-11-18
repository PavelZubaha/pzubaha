package pzubaha.set;

import java.util.NoSuchElementException;

/**
 * Chapter_005. Collection. Pro.
 * 4.Set
 * Task 997.
 * <p>
 * Class represents simple set data structure
 * based on linked list.
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
     * Last element.
     */
    private Node<E> last;

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
        private Node<E> prev;
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
            Node<E> addNode = new Node<E>(e);
            Node<E> pointer = first;
            //when there are elements present in the set.
            if (pointer != null) {
                int hash = e.hashCode();
                //before first
                if (hash < first.val.hashCode()) {
                    first.prev = addNode;
                    addNode.next = first;
                    first = addNode;
                    //after last
                } else if (hash > last.val.hashCode()) {
                    last.next = addNode;
                    addNode.prev = last;
                    last = addNode;
                    //add before the pointer position
                } else {
                    while (hash > pointer.val.hashCode()) {
                        pointer = pointer.next;
                    }
                    if (hash == pointer.val.hashCode()) {
                        result = !e.equals(pointer.val);
                    }
                    if (result) {
                        addNode.prev = pointer.prev;
                        addNode.next = pointer;
                        pointer.prev = addNode;
                        if (addNode.prev == null) {
                            first = addNode;
                        } else {
                            addNode.prev.next = addNode;
                        }
                    }
                }
            } else {
                //when there are no elements present in the set
                first = addNode;
                last = addNode;
            }
        }
        if (result) {
            length++;
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
