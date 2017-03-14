package ru.job4j.max;
/**.
 *Class contents solution of task 189
 *@author pzubaha
 *@since 1
 *@version 1
*/
public class Max {
	/**
	*method return biggest value of two numbers.
	*@param first - first number
	*@param second - second number
	*@return result
	*/
	public int max(int first, int second) {
		return ((first > second) ? first : second);
	}
	/**
	*method return biggest value of 3 numbers.
	*@param first - first number
	*@param second - second number
	*@param third - third number
	*@return result
	*/
	public int max(int first, int second, int third) {
		return max(max(first, second), third);
	}
}