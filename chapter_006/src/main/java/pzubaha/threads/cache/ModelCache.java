package pzubaha.threads.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * Chapter_006. Multithreading.
 * 5. Non-blocking algorithm.
 * 1. Non blocking cache.
 * <p>
 * Contains solution of task 4741.
 * Class implements simple non-blocking cache.
 * Created 18.04.2018.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class ModelCache<K, V> {

    /**
     * Map for persist.
     */
    private final ConcurrentHashMap<K, Item<V>> map = new ConcurrentHashMap<>();

    /**
     * Default constructor.
     */
    public ModelCache() {
    }

    /**
     * Add method.
     * @param k key to associate next specified value.
     * @param v stored Item container.
     * @return false if there is present some value mapped to the k, otherwise true.
     */
    public boolean add(K k, Item<V> v) {
        return map.putIfAbsent(k, v) == null;
    }

    /**
     * Update method.
     * @param key key
     * @param upDateValue value to update.
     * @return true if stored value is updated, otherwise false.
     */
    public boolean update(K key, Item<V> upDateValue) {
        final boolean[] updated = {false};
        map.computeIfPresent(key, (key1, value) -> {
            if (value.getVersion() == upDateValue.getVersion()) {
                value.updateContext(upDateValue.getContext());
                updated[0] = true;
            } else {
                throw new OptimisticException(String.format("Concurrent modification of the item container %s", value.toString()));
            }
            return value;
        });
        return updated[0];
    }

    /**
     * Method for deleting value by the key.
     * @param key witch associated with some value.
     * @return true if associated with the key value is updated by null value.
     */
    public boolean delete(K key) {
        final boolean[] deleted = {false};
        map.computeIfPresent(key, (key1, v) -> {
            deleted[0] = true;
            return null;
        });
        return deleted[0];
    }

    /**
     * Simple getter for the Item, associated with the key.
     * @param key special key, that mapped with value.
     * @return value witch associated with the key.
     */
    public Item<V> get(K key) {
        return map.getOrDefault(key, new Item<V>(null));
    }

}

