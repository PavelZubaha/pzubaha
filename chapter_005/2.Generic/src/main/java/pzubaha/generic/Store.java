package pzubaha.generic;

/**
 * Chapter_005. Collection. Pro.
 * Generic.
 * <p>
 * Contains solution of task 157
 * Interface for some store.
 * Created 25.10.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public interface Store<T extends Base> {
    void add(T model);
    boolean update(T model, String id);
    boolean delete(String id);
}
