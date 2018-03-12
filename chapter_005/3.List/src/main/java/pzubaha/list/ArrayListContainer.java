package pzubaha.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Chapter_005. Collection. Pro.
 * List.
 * <p>
 * Contains solution of task 1105.
 * Class implements simple common container.
 * Thread safe implementation.
 * Created 04.11.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 * @param <E> element type.
 */
@ThreadSafe
public class ArrayListContainer<E> implements AbstractContainer<E> {
    /**
     * Data's of stored element.
     */
    @GuardedBy(value = "this")
    private Object[] objects;
    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 32;

    /**
     * Next element index.
     */
    @GuardedBy("this")
    private int nextElementIndex = 0;

    /**
     * Constructor.
     * @param initCapacity - initial capacity of container.
     * @throws IllegalArgumentException - throws when initial capacity is less then 1, or
     * bigger then Integer.MAX_VALUE - 8.
     */
    public ArrayListContainer(final int initCapacity) {
        if (initCapacity > 0 && Integer.MAX_VALUE - 8 - initCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + initCapacity);
        }
        objects = new Object[initCapacity > DEFAULT_CAPACITY ? initCapacity : DEFAULT_CAPACITY];
    }

    /**
     * Adding element to container.
     * @param value - element to add.
     */
    public synchronized final void add(final E value) {
        if (value != null) {
            if (objects.length <= nextElementIndex) {
                grow();
            }
            objects[nextElementIndex++] = value;
        }
    }

    /**
     * Simple get method.
     * @param index index of requested element.
     * @return element.
     */
    @SuppressWarnings("uncheked")
    public synchronized final E get(final int index) {
        if (index < 0 || index >= nextElementIndex) {
            throw new NoSuchElementException();
        }
        return (E) objects[index];
    }

    /**
     * Method for extending container.
     */
    private synchronized void grow() {
        Object[] newArray = new Object[objects.length << 1];
        System.arraycopy(objects, 0, newArray, 0, nextElementIndex);
        objects = newArray;
    }

    /**
     * Get length of container.
     * @return length of added element sequence.
     */
    public synchronized final int length() {
        return nextElementIndex;
    }

    /**
     * iterator.
     * @return Iterator instance.
     */
    @Override
    public synchronized final Iterator<E> iterator() {
        return new Iterator<E>() {
            private int pointer = 0;
            @Override
            public boolean hasNext() {
                synchronized (ArrayListContainer.this) {
                    return pointer < nextElementIndex;
                }
            }
            @Override
            public E next() {
                E result;
                synchronized (ArrayListContainer.this) {
                    if (hasNext()) {
                        result = get(pointer++);
                    } else {
                        String msg = String.valueOf("There is no more elements");
                        throw new NoSuchElementException(msg);
                    }
                    return result;
                }
            }
        };
    }
}