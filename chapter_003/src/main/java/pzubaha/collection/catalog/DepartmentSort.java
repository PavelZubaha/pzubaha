package pzubaha.collection.catalog;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Chapter_003. Collections. Lite.
 * Test task.
 * <p>
 * Contains solution of task 10038.
 * User class.
 * Created 12.10.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class DepartmentSort {
    /**
     * Compare method for comparator's.
     * @param o1 first compared string.
     * @param o2 second string to compare.
     * @param sign 1 or -1 depend on strict order or reverse order.
     * @return compare method.
     */
    private int compareWithSign(String o1, String o2, int sign) {
        int result;
        int length1 = o1.length();
        int length2 = o2.length();
        int length = length1 < length2 ? length1 : length2;
        int subResult = o1.substring(0, length).compareTo(o2.substring(0, length));
        if (subResult == 0) {
            result = length1 < length2 ? -1 : length2 < length1 ? 1 : 0;
        } else {
            result = sign * subResult;
        }
        return result;
    }
    /**
     * Sort List of department codes.
     * @param list original list
     * @return sorted List<String> in A to Z order.
     */
    public List<String> sortByDirectOrder(List<String> list) {
        checkContainsAll(list);
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return compareWithSign(o1, o2, 1);
            }
        });
        return list;
    }

    /**
     * Method for check and complement list of department codes.
     * @param list original list
     */
    private void checkContainsAll(List<String> list) {
        List<String> additionalList = new ArrayList<>();
        String substring;
        for (String depCode : list) {
            if (depCode.contains("\\")) {
                for (int index = 0; index != depCode.length(); index++) {
                    index = depCode.indexOf("\\", index);
                    if (index == -1) {
                        break;
                    }
                    substring = depCode.substring(0, index);
                    if (!list.contains(substring) && !additionalList.contains(substring)) {
                        additionalList.add(substring);
                    }
                }
            }
        }
        if (additionalList.size() > 0) {
            list.addAll(additionalList);
        }
    }

    /**
     *
     * @param list original list
     * @return list sorted by revers order.
     */
    public List<String> sortByReversOrder(List<String> list) {
        checkContainsAll(list);
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return compareWithSign(o1, o2, -1);
            }
        });
        return list;
    }
}
