package ru.job4j.loop;
/**.
 *Class Factorial contents solution of task 193
 *@author pzubaha
 *@since 1
 *@version 1
*/
public class Factorial {
	/**
	*method calculate factorial number.
	*@param n - number we calculate factorial.
	*@return factorial value
	*/
	public double calc(int n) {
		double factorial = 1;
		while (n > 1) {
			factorial *= n;
			n--;
		}
		return factorial;
	}
}