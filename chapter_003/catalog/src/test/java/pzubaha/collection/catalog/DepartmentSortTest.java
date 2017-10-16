package pzubaha.collection.catalog;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Chapter_003. Collections. Lite.
 * Generic.
 * <p>
 * Contains solution of task 10093.
 * Class for Converting List to Map.
 * Created 16.10.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class DepartmentSortTest {
    /**
     * Test sorting by revers order.
     */
    @Test
    public void sortByReversOrder() {
        //create tested list
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"));

        //create tested instance
        DepartmentSort ds = new DepartmentSort();

        //expected list
        List<String> expectedList = new ArrayList<>();
        expectedList.addAll(Arrays.asList(
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK2",
                "K2\\SK1\\SSK1",
                "K1",
                "K1\\SK2",
                "K1\\SK1",
                "K1\\SK1\\SSK2",
                "K1\\SK1\\SSK1"
                ));
        //assertion
        assertTrue(ds.sortByReversOrder(list).equals(expectedList));
    }

    /**
     * Test sortFromAToZ method.
     */
    @Test
    public void sortByDirectOrder() {
        //create tested list
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"));

        //create tested instance
        DepartmentSort ds = new DepartmentSort();

        //expected list
        List<String> expectedList = new ArrayList<>();
        expectedList.addAll(Arrays.asList(
                "K1",
                "K1\\SK1",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K1\\SK2",
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"));
        //assertion
        assertTrue(ds.sortByDirectOrder(list).equals(expectedList));
    }
}