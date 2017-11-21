package pzubaha.set;

import java.util.NoSuchElementException;

/**
 * Chapter_005. Collection. Pro.
 * 4.Set.
 * Task 996.
 * Class represents a simple set structure,
 * based on unsorted array
 * Created 16.11.2017.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 *
 * @param <E> - parametrized type.
 */
public class ArraySet<E> implements SimpleSet<E> {
    /**
     * Amount of added elements.
     */
    private int amount = 0;
    /**
     * Pointer for iterate elements.
     */
    private int pointer = 0;
    /**
     * Element data.
     */
    private Object[] data;
    /**
     * Default capacity.
     */
    private static final int DEF_CAP = 16;

    /**
     * Constructor.
     * @param capacity initial capacity.
     */
    public ArraySet(final int capacity) {
        this.data = new Object[capacity > DEF_CAP ? capacity : DEF_CAP];
    }

    /**
     * Default constructor.
     */
    public ArraySet() {
        this.data = new Object[DEF_CAP];
    }

    /**
     * Check is specified value there.
     * @param value value to check.
     * @return true if container contains it,
     * otherwise false.
     */
    public boolean contains(E value) {
        return value != null && amount != 0 && findIndByVal(value) != -1;
    }

    /**
     * Method try to find specified value.
     * @param value value to find.
     * @return index of element that equals the specified,
     * otherwise -1.
     */
    @SuppressWarnings("unchecked")
    private int findIndByVal(E value) {
        int result = -1;
        for (int j = 0; j != amount; j++) {
            if (((E) data[j]).equals(value)) {
                result = j;
                break;
            }
        }
        return result;
    }

    /**
     * Add element to this set.
     * @param e element to add.
     * @return true if element have added, otherwise false.
     */
    public boolean add(E e) {
        boolean result = false;
        if (amount == data.length) {
            ensureCapacity();
        }
        if (e != null) {
            int index = findIndByVal(e);
            if (index == -1) {
                data[amount++] = e;
                result = true;
            }
        }
        return result;
    }

    /**
     * Provide enough capacity.
     */
    private void ensureCapacity() {
        Object[] newData = new Object[amount << 1];
        System.arraycopy(data, 0, newData, 0, amount);
        data = newData;
    }
    /**
     * Get amount of elements.
     * @return amount of present elements.
     */
    public int amount() {
        return amount;
    }

    /**
     * Method for checking has this set more elements.
     * @return true if there are present more elements, otherwise false.
     */
    @Override
    public boolean hasNext() {
        return pointer < amount;
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
        return (E) data[pointer++];
    }

    /**
     * For reset pointer to first stored value.
     */
    public void resetPointer() {
        pointer = 0;
    }

    /**
     * Method for removing elements.
     * @param e value that needs to be removed.
     * @return true if element had returned, otherwise false.
     */
    public boolean remove(E e) {
        boolean result = false;
        if (e != null) {
            int index = findIndByVal(e);
            if (index != -1) {
                System.arraycopy(data, index + 1, data, index, amount - index - 1);
                data[--amount] = null;
                result = true;
            }
        }
        return result;
    }
}
