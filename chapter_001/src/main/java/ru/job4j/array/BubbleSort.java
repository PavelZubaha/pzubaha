package ru.job4j.array;
/**Sorting array from the smallest value to the largest one.
 *Solution for task 195
 *@author Pavel Zubaha
 *@since 16.03.17
 *@version 1
*/
public class BubbleSort {
	/**sorts using two ciycles.
	*@param array - original array.
	*@return sorted array.
	*/
	public int[] sort(int[] array) {
		int arrayLength = array.length;
		int temp = 0;
		for (int index = 0; index != arrayLength; index++) {
			for (int j = 1; j != arrayLength - index; j++) {
				if (array[j] < array[j - 1]) {
				temp = array[j];
				array[j] = array[j - 1];
				array[j - 1] = temp;
				}
			}
		}
		return array;
	}
}