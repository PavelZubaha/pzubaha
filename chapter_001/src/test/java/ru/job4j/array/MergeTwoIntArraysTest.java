package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**Test for merging array of int.
 *
 * Part 1. Base syntax.
 * Lesson 6. Additional questions
 * Task: Test a program for merging two sorted arrays of int into a new one.
 *
 * @author Zubaha Pavel
 * @version 1
 * @since 16.04.2017
 */
public class MergeTwoIntArraysTest {
	/**
	 *Test case when first array has three elements,  second array has four.
	*/
	@Test
	public void whenFirstArrayHasThreeElementsSecondArrayHasFourThenMergedArray() {
		//asign
		MergeTwoIntArrays mergeArray = new MergeTwoIntArrays();
		int[] firstArray = {1, 2, 5};
		int[] secondArray = {3, 4, 6, 8};
		int[] expectedArray = {1, 2, 3, 4, 5, 6, 8};
		//act
		assertThat(mergeArray.merge(firstArray, secondArray), is(expectedArray));
	}
	/**
	 *Test case when lenght of first array is 0,  second array has four elements.
	*/
	@Test
	public void whenLenghtOfFirstArrayIsZeroSecondArrayHasFourElementsThenMergedArray() {
		//asign
		MergeTwoIntArrays mergeArray = new MergeTwoIntArrays();
		int[] firstArray = new int[0];
		int[] secondArray = {3, 4, 6, 8};
		int[] expectedArray = {3, 4, 6, 8};
		//act
		assertThat(mergeArray.merge(firstArray, secondArray), is(expectedArray));
	}
	/**
	*Test case when lenght of second array is 0,  first array has four elements.
	*/
	@Test
	public void whenLenghtOfSecondArrayIsZeroFirstArrayHasFourElementsThenMergedArray() {
		//asign
		MergeTwoIntArrays mergeArray = new MergeTwoIntArrays();
		int[] firstArray = {7, 8, 9, 10};
		int[] secondArray = new int[0];
		int[] expectedArray = {7, 8, 9, 10};
		//act
		assertThat(mergeArray.merge(firstArray, secondArray), is(expectedArray));
	}
	/**
	*Test case when arrays has several equal elements.
	*/
	@Test
	public void whenArraysHasSeveralEqualElementsThenMergedArray() {
		//asign
		MergeTwoIntArrays mergeArray = new MergeTwoIntArrays();
		int[] firstArray = {7, 8, 8, 10, 90};
		int[] secondArray = {7, 7, 8, 10, 80, 100};
		int[] expectedArray = {7, 7, 7, 8, 8, 8, 10, 10, 80, 90, 100};
		//act
		assertThat(mergeArray.merge(firstArray, secondArray), is(expectedArray));
	}
}