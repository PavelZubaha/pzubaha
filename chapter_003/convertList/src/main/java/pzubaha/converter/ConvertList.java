package pzubaha.converter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Chapter_003. Collections. Lite.
 * Collections Framework.
 *
 * Contains solution of task 10035.
 * Class represent method's for converting two-dimensional array to ArrayList
 * and for converting ArrayList to two-dimensional array.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 25.09.17
 * @version 1
 */
public class ConvertList {

    /**
     * Method converts  two-dimensional array of int to ArrayList<Integer>.
     * @param array original two-dimensional array of int.
     * @return ArrayList<Integer>
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> result = new ArrayList<>(array.length * array[0].length);
        for (int[] rowArray : array) {
            for (Integer element : rowArray) {
                result.add(element);
            }
        }
        return result;
    }

    /**
     * Method converts List<Integer> to two-dimensional array of int.
     * @param list original List of Integer.
     * @param rows required amount of rows.
     * @return two-dimensional array.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        if (rows <= 0) {
            rows = 1;
        }
        int[][] result;
        if (list.size() % rows != 0) {
            result = new int[rows][list.size() / rows + 1];
        } else {
            result = new int[rows][list.size() / rows];
        }
        Iterator<Integer> iterator = list.iterator();
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[i].length; j++) {
                if (iterator.hasNext()) {
                    result[i][j] = iterator.next();
                } else {
                    result[i][j] = 0;
                }
            }
        }
        return result;
    }

    /**
     * Convert two-dimensional array to String.
     * @param array two-dimensional array of int.
     * @return representation array as String
     */
    public String arrayToString(int[][] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int j = 0; j != array.length; j++) {
            sb.append("{");
            for (int i = 0; i != array[j].length; i++) {
                if (i == array[j].length - 1) {
                    sb.append(array[j][i]);
                    continue;
                }
                sb.append(array[j][i]);
                sb.append(", ");
            }
            sb.append("}");
            if (j != array.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    /**
     * Convert List<int[]> to List<Integer>.
     * @param listOriginal - original List<int[]>
     * @return - List<Integer> that contain all element of listOriginal.
     */
    public List<Integer> convert(List<int[]> listOriginal) {
        List<Integer> result = new ArrayList<>();
        for (int[] elementaryArray : listOriginal) {
            for (int element : elementaryArray) {
                result.add(element);
            }
        }
        return result;
    }
}