package ru.job4j.array;

/**
 * Part 1. Base syntax.
 * Lesson 6. Additional questions
 * Task: Create a program for merging two sorted arrays of int into a new one.
 *
 * @author Zubaha Pavel
 * @version 1
 * @since 15.04.2017
 */
public class MergeTwoIntArrays {
	/**
	*merge two sorted int arrays.
	*@param a - first original sorted array of int.
	*@param b - second original sorted array of int.
	*@return merged sorted array of int.
	*/
	public int[] merge(int[] a, int[] b) {
		int aLength = a.length;
		int bLength = b.length;
		int newArrayLength = aLength + bLength;
		int[] mergedArray = new int[newArrayLength];
		for (int i = 0, indexA = 0, indexB = 0; i != newArrayLength; i++) {
			if (indexA != aLength && indexB == bLength || indexA != aLength && indexB != bLength && a[indexA] < b[indexB]) {
					mergedArray[i] = a[indexA];
					indexA++;
					continue;
			} else if (indexB != bLength && indexA == aLength || indexA != aLength && indexB != bLength && a[indexA] >= b[indexB]) {
					mergedArray[i] = b[indexB];
					indexB++;
					continue;
					}
		}
		return mergedArray;
	}
}