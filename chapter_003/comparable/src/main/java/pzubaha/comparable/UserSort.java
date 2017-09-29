package pzubaha.comparable;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Chapter_003. Collections. Lite.
 * Sort.
 * <p>
 * Contains solution of task 10034.
 * Comparable interface implementation and using for sorting,
 * and generating Tree data-structure.
 *
 * Created 28.09.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class UserSort {
    /**
     * Convert collection of User from list to set.
     * @param list original list of User's.
     * @return User'set.
     */
    public Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }
}
