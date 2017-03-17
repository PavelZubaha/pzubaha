package ru.job4j.array;

/**Rotate square array.
 *Solution for task 223. Arrays.
 *@author Pavel Zubaha
 *@since 17.03.17
 *@version 1
*/
public class RotateArray {
	/**method rotates array.
	*@param array - original square array.
	*@return rotated array.
	*/
	public int[][] rotate(int[][] array) {
		int temp = 0, j;
		int arL = array.length;
		int maxIteration = (int) arL / 2;
		for (int i = 0; i != maxIteration; i++) {
			for (j = i; j != arL  - 1 - i; j++) {
				temp = array[i][j];
				array[i][j] = array[arL - j - 1][i];
				array[arL - j - 1][i] = array[arL - i - 1][arL - j - 1];
				array[arL - i - 1][arL - j - 1] = array[j][arL - i - 1];
				array[j][arL - i - 1] = temp;
			}
		}
		return array;
	}
}