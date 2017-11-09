package pzubaha.list;

import java.util.EmptyStackException;

/**
 * Chapter_005. Collection. Pro.
 * 3.List.
 * <p>
 * Contains solution of task 160.
 * Class Stack.
 * Represents a last-in-first-out (LIFO) stack of objects.
 * Created 07.11.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class StackList<E> extends LinkedListContainer<E> {

    /**
     * Add object to the top of this stack.
     * @param e object to add to the top of this stack.
     */
    public void push(E e) {
        add(e);
    }

    /**
     * Removes the object at the top of this stack and returns that.
     * @return the object at the top of this stack.
     * @throws EmptyStackException if the stack is empty.
     */
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return remove(size() - 1);
    }

    /**
     * Get next element of this stack without removing from stack.
     * @return element.
     * @throws EmptyStackException if the stack is empty.
     */
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return get(size() - 1);
    }
}
