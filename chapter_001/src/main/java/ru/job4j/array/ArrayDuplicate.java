package ru.job4j.array;

import static java.util.Arrays.copyOf;

/**
 * Part 1. Base syntax.
 * Lesson 5. Arrays
 * Task 5.3. 225 Create a program for removing duplicates from array of String
 *
 * @author Zubaha Pavel
 * @version 1
 * @since 19.03.2017
 */
public class ArrayDuplicate {
	/**
	*remove duplicates.
	*@param array - original array.
	*@return array without duplicate objects
	*/
	public String[] remove(String[] array) {
		int arLn = array.length, j, x, index = 0;
		boolean isCopy = false;
		while (index < arLn - 1) {
			for (j = arLn - 1; j > index; j--) {
				if (array[index].equals(array[j])) {
					isCopy = true;
					arLn--;
					x = j;
					while (x < arLn) {
						array[x] = array[x + 1];
						x++;
					}
				}
			}
			index++;
		}
		return isCopy ? copyOf(array, arLn) : array;
	}
}