package pzubaha.tree;
import java.util.Optional;

/**
 * Chapter_005. Collection. Pro.
 * 6.Tree.
 * <p>
 * Contains solution of task 1711.
 * Interface of required class.
 * Created 26.12.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public interface SimpleTree<T extends Comparable<T>> extends Iterable<T> {
    /**
     * Add element to parent.
     * Parent may had the list of child.
     * @param parent parent.
     * @param child child.
     * @return true if element added.
     */
    boolean add(T parent, T child);

    /**
     * Find node by value and returned it Optional container.
     * @param value value.
     * @return Optional object.
     */
    Optional<Node<T>> findBy(T value);
}
