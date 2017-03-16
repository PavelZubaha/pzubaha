package ru.job4j.turn;

/**Turns array to be inverted.
 *Solution on task 4441.
 *@author Zubaha Pavel.
 *@since 16.03.17
 *@version 1
*/
public class Turn {
	/**inverting array.
	*@param array - original array.
	*@return inverted array.
	*/
	public static int[] back(int[] array) {
		int tmp = 0;
		int arrayLength = array.length;
		for (int indexStart = 0, indexEnd = arrayLength - 1; indexStart < indexEnd; indexStart++, indexEnd--) {
			tmp = array[indexStart];
			array[indexStart] = array[indexEnd];
			array[indexEnd] = tmp;
		}
		return array;
	}
}