package pzubaha.set;

/**
 * Chapter_005. Collection. Pro.
 * 4.Set.
 * SimpleHashSet.
 * <p>
 * Contains solution of task 998.
 * Class represents hash set data structure.
 * Based on hash table.
 * Created 20.11.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class SimpleHashSet<V> {
    /**
     * Array of element containers.
     */
    private ArrayHashSet<V>[] entrySet;
    /**
     * Amount of added elements.
     */
    private int amount = 0;

    /**
     * Constructor.
     * @param initialSize initial size of array that stored the buckets.
     */
    @SuppressWarnings("unchecked")
    public SimpleHashSet(int initialSize) {
        entrySet = new ArrayHashSet[initialSize > 16 ? initialSize : 16];
    }

    /**
     * Default constructor.
     */
    public SimpleHashSet() {
        this(16);
    }

    /**
     * Add elements to this hash set.
     * @param value value to add.
     * @return true if element have add, otherwise false.
     */
    public boolean add(V value) {
        boolean result = false;
        if (value != null) {
            int positionToAdd = value.hashCode() % entrySet.length;
            if (entrySet[positionToAdd] == null) {
                entrySet[positionToAdd] = new ArrayHashSet<>();
            }
            result = entrySet[positionToAdd].add(value);
            if (result) {
                amount++;
            }

        }
        return result;
    }

    /**
     * Method checks is there specified value present in the set.
     * @param value value for check.
     * @return true if specified value present in the set,
     * otherwise false.
     */
    public boolean contains(V value) {
        return entrySet[value.hashCode() % entrySet.length].contains(value);
    }

    /**
     * Method for removing elements from the set.
     * @param value value that needs to be removed.
     * @return true if element had returned, otherwise false.
     */
    public boolean remove(V value) {
        if (entrySet[value.hashCode() % entrySet.length].remove(value)) {
            amount--;
            return true;
        }
        return false;
    }

    /**
     * Get amount of present elements.
     * @return amount.
     */
    public int getAmount() {
        return amount;
    }
}
