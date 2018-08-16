package pzubaha.map;

/**
 * Chapter_005. Collection. Pro.
 * Map. HashMap.
 * <p>
 * Contains solution of task 1008.
 * Task represented like interface.
 * Created 12.12.2017.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public interface SimpleMap<K, V> extends Iterable<K> {
    /**
     * Method for inserting elements.
     * @param key key with which the specified value is to be associated.
     * @param value specified value.
     * @return true if element added, otherwise - false.
     */
    boolean insert(K key, V value);

    /**
     * Method for getting value to which the specified key is mapped.
     * @param key key.
     * @return the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     */
    V get(K key);

    /**
     * Method for deleting pairs key-value.
     * @param key key.
     * @return true if element deleted, otherwise false.
     */
    boolean delete(K key);
}
