package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**Test array sorting.
 *Solution on task 195.
 *@author Zubaha Pavel.
 *@since 16.03.17
 *@version 1
*/
public class BubbleSortTest {
	/**
	 *Test when array contents ten int elements.
	*/
	@Test
	public void whenSortArrayWithTenElementsThenSortedArray() {
		//asign
		BubbleSort bubbleSort = new BubbleSort();
		int[] array = new int[]{10, 9, 8, 7, 6, 0, 1, 3, 2, 5};
		int[] expectedArray = new int[]{0, 1, 2, 3, 5, 6, 7, 8, 9, 10};
		//act
		assertThat(bubbleSort.sort(array), is(expectedArray));
	}
}