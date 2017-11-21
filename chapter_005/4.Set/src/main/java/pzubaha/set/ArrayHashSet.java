package pzubaha.set;

import java.util.NoSuchElementException;

/**
 * Chapter_005. Collection. Pro.
 * 4.Set.
 * Task 996.
 * Class represents a simple set structure,
 * based on sorted by hash codes array of objects.
 * Created 16.11.2017.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 *
 * @param <E> - parametrized type.
 */
public class ArrayHashSet<E> implements SimpleSet<E> {
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
    public ArrayHashSet(final int capacity) {
        this.data = new Object[capacity > DEF_CAP ? capacity : DEF_CAP];
    }

    /**
     * Default constructor.
     */
    public ArrayHashSet() {
        this.data = new Object[DEF_CAP];
    }

    /**
     * Check is specified value there.
     * @param value value to check.
     * @return true if container contains it,
     * otherwise false.
     */
    public boolean contains(E value) {
        boolean result = false;
        if (value != null && amount != 0) {
            int index = findIndByHash(0, amount - 1, value.hashCode());
            if (index != -1 && index != amount) {
                result = data[index].equals(value);
            }
        }
        return result;
    }

    /**
     * Method fins position of hash code value.
     * @param from search start index.
     * @param to search finish index.
     * @param hashCode hash code value to search.
     * @return founded index or
     * amount if the specified hashcode bigger
     * then the biggest hashcode of stored elements,
     * or -1 if smaller then smallest of stored elements.
     */
    private int findIndByHash(int from, int to, int hashCode) {
        int index = -1, mid, midHash;
        if (amount != 0) {
            if (hashCode > data[to].hashCode()) {
                index = amount;
            } else if (hashCode >= data[from].hashCode()) {
                do {
                    mid = (from + to) >>> 1;
                    index = mid;
                    midHash = data[mid].hashCode();
                    if (hashCode == midHash) {
                        break;
                    } else {
                        if (hashCode < midHash) {
                            to = mid;
                        } else {
                            from = mid + 1;
                            index = from;
                        }
                    }
                } while (to - from >= 1);
            }
        }
        return index;
    }

    /**
     * Add element to this set.
     * @param e element to add.
     * @return true if element have added, otherwise false.
     */
    public boolean add(E e) {
        if (e == null) {
            return false;
        }
        boolean result = true;
        if (amount == data.length) {
            this.ensureCapacity();
        }
        int index = findIndByHash(0, amount - 1, e.hashCode());
        if (index == amount) {
            data[amount++] = e;
        } else {
            if (index == -1) {
                index = 0;
            } else {
                result = !(e.equals(data[index]));
            }
            if (result) {
                System.arraycopy(data, index, data, index + 1, amount - index);
                amount++;
                data[index] = e;
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
            int index = findIndByHash(0, amount - 1, e.hashCode());
            if (index != -1 && index != amount) {
                result = data[index].equals(e);
                if (result) {
                    System.arraycopy(data, index + 1, data, index, amount - index - 1);
                    data[--amount] = null;
                }
            }
        }
        return result;
    }
}
