package pzubaha.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Chapter_005. Collection. Pro.
 * List.
 * <p>
 * Contains solution of task 159.
 * Class represents simple linked list.
 * Created 06.11.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class LinkedListContainer<E> implements AbstractContainer<E> {
    /**
     * First Node.
     */
    private Node<E> first = null;

    /**
     * Last Node.
     */
    private Node<E> last = null;

    /**
     * Size of linked sequence.
     */
    private int size = 0;

    /**
     * Add element method to the end of sequence.
     * @param e element to add.
     */
    @Override
    public final void add(E e) {
        if (e != null) {
            if (first != null) {
                last.next = new Node<E>(e);
                last.next.previous = last;
                last = last.next;
            } else {
                first = new Node<E>(e);
                last = first;
            }
            size++;
        }
    }

    /**
     * Add element to specified position
     * @param e element to add.
     * @param position specified position of element to add,
     *                 it begins from 0.
     */
    public final void add(E e, int position) {
        if (position > size) {
            throw new IndexOutOfBoundsException();
        }
        if (e != null) {
            if (position == size) {
                this.add(e);
            } else {
                Node<E> node = new Node<>(e);
                boolean flag = false;
                if (position == 0) {
                    node.linkNext(first);
                    first = node;
                    flag = true;
                }
                if (position == size - 1) {
                    node.linkPrevious(last.previous);
                    node.linkNext(last);
                    flag = true;
                }
                if (!flag && size > 2) {
                    Node<E> previousOfSpecified = first;
                    for (int i = 1; i < position; i++) {
                        previousOfSpecified = previousOfSpecified.next;
                    }
                    node.linkNext(previousOfSpecified.next);
                    node.linkPrevious(previousOfSpecified);
                }
                size++;
            }
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Get E element from specified position.
     * @param index specified index of requested element.
     * @return E element.
     */
    @Override
    public final E get(int index) {
        return findNode(index).element;
    }

    /**
     * iterator method, for implementing Iterable.
     * @return Iterator.
     */
    @Override
    public final Iterator<E> iterator() {
        return new It();
    }

    /**
     * Get size.
     * @return size.
     */
    public final int size() {
        return size;
    }

    /**
     * Remove element by index
     * @param index index of element to delete
     * @return wrapped item in the removing Node.
     */
    public final E remove(int index) {
        final Node<E> x = findNode(index);
        final Node<E> prev = x.previous;
        final Node<E> nxt = x.next;
        final E result = x.element;
        //delete from center
        if (index != 0 && index != size - 1) {
            prev.linkNext(nxt);
        } else {
            //delete first
            if (first == x) {
                first = nxt;
                if (nxt != null) {
                    nxt.previous = null;
                }
            } else {
                //delete last
                last = prev;
                if (prev != null) {
                    prev.next = null;
                }
            }
        }
        size--;
        x.next = null;
        x.previous = null;
        x.element = null;
        return result;
    }

    /**
     * Find Node by index.
     * @param index index.
     * @return Node.
     * @throws NoSuchElementException when index not correct.
     */
    private Node<E> findNode(int index) {
        if (index >= 0 && index < size) {
            Node<E> result;
            if (index < size >> 1) {
                result = first;
                for (int i = 0; i < index; i++) {
                    result = result.next;
                }
            } else {
                result = last;
                for (int i = size - 1; i > index; i--) {
                    result = result.previous;
                }
            }
            return result;
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Iterator class for implements Iterable by LinkedListContainer.
     */
    private class It implements Iterator<E> {
        Node<E> itNext = first;
        public boolean hasNext() {
            return itNext != null;
        }
        public E next() {
            if (hasNext()) {
                Node<E> result = itNext;
                itNext = itNext.next;
                return result.element;
            } else {
                throw new NoSuchElementException();
            }
        }
    }
    /**
     * Class for store and link.
     * @param <E> parametrized type.
     */
    private static class Node<E> {
        /**
         * Element.
         */
        E element;

        /**
         * Link to previous Node.
         */
        Node<E> previous;

        /**
         * Link to next Node.
         */
        Node<E> next;

        /**
         * Constructor.
         * @param value value which will be stored.
         */
        Node(E value) {
            this.element = value;
        }

        /**
         * Connect this Node to another, which will be next.
         * @param another another Node.
         */
        void linkNext(Node<E> another) {
            this.next = another;
            another.previous = this;
        }

        /**
         * Connect this Node to another, which will be previous.
         * @param another another Node.
         */
        void linkPrevious(Node<E> another) {
            this.previous = another;
            another.next = this;
        }
    }
}
