package pzubaha.list;

import java.util.NoSuchElementException;

/**
 * Chapter_005. Collection. Pro.
 * List.
 * <p>
 * Contains solution of task 160.
 * Class represents queue data structure.
 * Represents a first-in-first-out (FIFO) queue of objects.
 * Created 09.11.2017.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class QueueList<E> extends LinkedListContainer<E> {
    /**
     * Retrieves and removes the head of this queue.
     * @return E-element the head of this queue.
     */
    public E poll() {
       if (isEmpty()) {
           throw new NoSuchElementException();
       }
       return remove(0);
    }

    /**
     * Retrieves head of the queue.
     * @return E-element the head of this queue.
     */
    public E peek() {
       if (isEmpty()) {
           throw new NoSuchElementException();
       }
       return get(0);
    }

    /**
     * Inserts specified element to the queue.
     * @param e element to add.
     */
    public void offer(E e) {
       add(e);
    }
}
