package pzubaha.set;

import java.util.Iterator;

/**
 * Chapter_005. Collection. Pro.
 * 4. Set.
 * <p>
 * Interface for different set classes.
 * Created 18.11.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public interface SimpleSet<E> extends Iterator<E> {
    /**
     * Method for adding elements.
     * @param e element that needs add.
     * @return true if element added, otherwise false.
     */
    boolean add(E e);
}
