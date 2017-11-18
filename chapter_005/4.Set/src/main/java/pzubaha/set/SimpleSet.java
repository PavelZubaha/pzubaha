package pzubaha.set;

import java.util.Iterator;

/**
 * Chapter_005. Collection. Pro.
 * 4. Set.
 * <p>
 * Interface for different classes.
 * Created 18.11.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public interface SimpleSet<E> extends Iterator<E> {
    boolean add(E e);
}
