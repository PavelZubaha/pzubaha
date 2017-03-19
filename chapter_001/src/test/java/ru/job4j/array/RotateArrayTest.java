package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Test RotateArray class.
 *Solution for task 223. Arrays.
 *@author Pavel Zubaha
 *@since 17.03.17
 *@version 1
*/
public class RotateArrayTest {
	/**
	*Test case when array[2][2].
	*/
	@Test
	public void whenArraySideLengthIsTwo() {
		//asign
		RotateArray rotateArray = new RotateArray();
		int[][] array = {{1, 2}, {3, 4}};
		int[][] expectedArray  = {{3, 1}, {4, 2}};
		int[][] resultArray = rotateArray.rotate(array);
		assertThat(resultArray, is(expectedArray));
	}
		/**
	*Test case when array[3][3].
	*/
	@Test
	public void whenArraySideLengthIsTree() {
		//asign
		RotateArray rotateArray = new RotateArray();
		int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		int[][] expectedArray  = {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
		int[][] resultArray = rotateArray.rotate(array);
		assertThat(resultArray, is(expectedArray));
	}
}