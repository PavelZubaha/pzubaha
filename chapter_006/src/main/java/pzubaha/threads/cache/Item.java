package pzubaha.threads.cache;

import java.util.Objects;

/**
 * Chapter_006. Multithreading.
 * 5. Non-blocking algorithm.
 * 1. Non blocking cache.
 * <p>
 * Contains solution of task 4741.
 * Class implements special container for storing value in ModelCache.
 * Created 18.04.2018.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
class Item<T> {
    /**
     * version of the Item.
     */
    private volatile int version = 0;
    /**
     * Stored context.
     */
    private T context;

    /**
     * Constructor.
     * @param context context, that will be stored.
     */
    public Item(T context) {
        this.context = context;
    }

    public int getVersion() {
        return version;
    }

    public T getContext() {
        return context;
    }

    /**
     * Method for updating context.
     * @param context new context.
     */
    public void updateContext(T context) {
        this.context = context;
        version++;
    }

    /**
     * Equals method
     * @param o object to check.
     * @return true if this Item equals specified object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item<?> item = (Item<?>) o;
        return Objects.equals(context, item.getContext());
    }

    /**
     * Hash code method.
     * @return int value of hash code of this Item instance.
     */
    @Override
    public int hashCode() {
        return Objects.hash(context);
    }

    /**
     * Method gives string representation of this Iem instance.
     * @return String value which is string representation.
     */
    @Override
    public String toString() {
        return String.format("Item{ version = %d context = %s }", version, context.toString());
    }
}
