package pzubaha.converter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

/**
 * Chapter_003. Collections. Lite.
 * Collections Framework.
 *
 * Contains solution of task 10035.
 * Test classes.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 25.09.17
 * @version 1
 */
public class ConvertListTest {
    /**
     * Test converting array to Arraylist.
     */
    @Test
    public void whenArrayConvertToArrayListThenHasOriginalElements() {

        //define array and expected ArrayList
        int[][] array = {{1, 2}, {3, 4}};
        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(1);
        expectedList.add(2);
        expectedList.add(3);
        expectedList.add(4);

        ConvertList convertList = new ConvertList();

        //checking.
        assertThat(convertList.toList(array), is(expectedList));
    }
    /**
     * Test converting List to two-dimensional array.
     */
    @Test
    public void whenListConvertToArrayThenArrayHasOriginalElementsAndRequiredRowAmount() {

        //define ArrayList and expected array
        int[][] expectedArray = {{1, 2}, {3, 4}, {0, 6}, {7, 0}};
        List<Integer> originalList = new ArrayList<>(7);
        for (int i = 1; i != 8; i++) {
            originalList.add(i);
        }
        originalList.set(4, null);

        ConvertList convertList = new ConvertList();

        assertArrayEquals(convertList.toArray(originalList, 4), expectedArray);
    }
    /**
     * Test converting Lits<int[]> to List<Integer>.
     */
    @Test
    public void whenListOfIntArraysConvertThenResultHasAllOriginalElements() {
        ConvertList convertList = new ConvertList();

        //define expected and original lists.
        List<int[]> listOriginal = new ArrayList<>();
        listOriginal.add(new int[]{1, 2});
        listOriginal.add(new int[]{3, 4, 5, 6});
        List<Integer> expected = new ArrayList<>();
        expected.addAll(Arrays.asList(1, 2, 3, 4, 5, 6));

        assertThat(convertList.convert(listOriginal), is(expected));
    }

}