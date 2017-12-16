package pzubaha.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Chapter_005. Collection. Pro.
 * Map. HashMap.
 * <p>
 * Contains solution of task 1008.
 * Class represents simple HashMap structure,
 * based on associated array,
 * without solution of collisions.
 * Created 12.12.2017.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class HashMap<K, V> implements SimpleMap<K, V> {

    /**
     * Array of entry data.
     */
    private Entry<K, V>[] tab;

    /**
     * Default size.
     */
    private static final int DEF_SIZE = 32;

    /**
     * Default constructor.
     */
    public HashMap() {
        this(DEF_SIZE);
    }

    /**
     * Getter for amount of stored elements.
     * @return
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Getter for current capacity.
     * @return current length of element array.
     */
    public int getCapacity() {
        return tab.length;
    }

    /**
     * Amount of stored elements.

     */
    private int amount = 0;

    /**
     * Constructor.
     * @param size initial size of data array.
     */
    @SuppressWarnings("unchecked")
    public HashMap(int size) {
        this.tab = new Entry[(DEF_SIZE > size) ? DEF_SIZE : size];
    }

    /**
     * Wrapper inner class for entry data.
     */
    private class Entry<K, V> {
        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        private K key;
        private V value;
    }

    /**
     * Method for inserting elements.
     * @param key key with which the specified value is associated.
     * @param value specified value.
     * @return true if element added, otherwise - false.
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean insert(K key, V value) {
        boolean result = false;
        int index = hashTabIndex(key);
        if (tab[index] == null) {
            if (amount > tab.length * 0.7) {
                grow();
            }
            tab[index] = new Entry(key, value);
            result = true;
            amount++;
        }
        return result;
    }

    private void insert(Entry<K, V> e) {
        insert(e.key, e.value);
    }

    /**
     * Method for extending current data.
     */
    @SuppressWarnings("unchecked")
    private void grow() {
        Entry<K, V>[] tmpTab = tab;
        tab = new Entry[tab.length * 2];
        amount = 0;
        for (Entry<K, V> element : tmpTab) {
            if (element != null) {
                insert(element);
            }
        }
    }

    /**
     * Method for getting value to which the specified key is mapped.
     * @param key key.
     * @return value, if some value to which the specified key is mapped, otherwise it returns null.
     */
    @SuppressWarnings("unchecked")
    @Override
    public V get(K key) {
        int index = hashTabIndex(key);
        return tab[index] == null ? null : (V) tab[index].value;
    }

    /**
     * Method for deleting pairs key-value.
     * @param key key.
     * @return true if element deleted, otherwise false.
     */
    @Override
    public boolean delete(K key) {
        boolean result = false;
        int index = hashTabIndex(key);
        if (tab[index] != null) {
            tab[index] = null;
            result = true;
            amount--;
        }
        return result;
    }

    /**
     * Method for getting iterator.
     * @return iterator as instance of anonymous class.
     */
    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int nextIndex = 0, nextCounter = 0;
            @Override
            public boolean hasNext() {
                return nextCounter < amount;
            }

            @SuppressWarnings("unchecked")
            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Call next, when no more elements");
                }
                nextCounter++;
                while (tab[nextIndex] == null) {
                    nextIndex++;
                }
                return (K) tab[nextIndex++].key;
            }
        };
    }

    /**
     * Calculation of tab index for specified key.
     * @param key key.
     * @return value of index for the specified key.
     */
    private int hashTabIndex(K key) {
        return (key == null) ? 0 : key.hashCode() % tab.length;
    }
}
