package pzubaha.generic;

import java.util.NoSuchElementException;

/**
 * Chapter_005. Collection. Pro.
 * Generic.
 * <p>
 * Contains solution of task 156.
 * Class for simple array implementation.
 * Created 24.10.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class SimpleArray<T> {
    /**
     * Array of objects.
     */
    private final Object[] objects;
    /**
     * Current pointer on array.
     */
    private int position = 0;

    /**
     * Constructor for simple array.
     * @param size - requred size.
     */
    public SimpleArray(int size) {
        objects = new Object[size];
    }

    /**
     * add method for simple array.
     * @param ref reference to parametrized object.
     */
    public void add(T ref) {
        if (position != objects.length) {
            objects[position++] = ref;
        }
    }

    /**
     * delete method for SimpleArray.
     * @param index - index of deleted obj.
     * @return true if obj deleted from the array.
     */
    public boolean delete(int index) {
        boolean result = false;
        if (index < position && index >= 0) {
            System.arraycopy(objects, index + 1, objects, index, position - 1 - index);
            objects[--position] = null;
            result = true;
        }
        return result;
    }

    /**
     * getter.
     * @param index array index to get.
     * @return value of array[index].
     */
    public T get(int index) {
        if (index >= 0 && index < position) {
            return (T) objects[index];
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Update method allow update some elements.
     * @param index - index of obj to update.
     * @param valueToUpdate - new value.
     * @return true if update operation have completed,
     * otherwise false.
     */
    public boolean update(int index, T valueToUpdate) {
        boolean result = false;
        if (index >= 0 && index < position && valueToUpdate != null) {
            objects[index] = valueToUpdate;
            result = true;
        }
        return result;
    }
}
