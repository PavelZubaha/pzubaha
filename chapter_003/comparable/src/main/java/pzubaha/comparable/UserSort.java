package pzubaha.comparable;

import java.util.Comparator;
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

    /**
     * Sort List<User> by name length using Comparator.
     * @param list - original User list.
     * @return original sorted list.
     */
    public List<User> sortNameLength(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = 0;

                if (o1 != null && o2 != null) {
                    result = o1.getName().length() - o2.getName().length();
                } else if (o1 != null) {
                    result = 1;
                } else if (o2 != null) {
                    result = -1;
                }
                return result;
            }
        });
        return list;
    }

    /**
     * Sorting List<User> with Comparator by name and age.
     * @param list original List<User>
     * @return sorted list.
     */
    public List<User> sortByAllFields(List<User> list) {

        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = 0;
                if (o1 != null && o2 != null) {
                    result = o1.getName().compareTo(o2.getName());
                    if (result == 0) {
                        result = o1.getAge() - o2.getAge();
                    }
                } else if (o1 != null) {
                    result = 1;
                } else if (o2 != null) {
                    result = -1;
                }
                return result;
            }
        });
        return list;
    }
}
