package pzubaha.list;

/**
 * Chapter_005. Collection. Pro.
 * List.
 * <p>
 * Contains solution of task 159
 * Interface for different containers.
 * Created 06.11.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public interface AbstractContainer<E> extends Iterable<E> {
    void add(E e);
    E get(int index);
}
