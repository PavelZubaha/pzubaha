package ru.job4j.loop;
/**.
 *Class Counter contents solution of task 192
 *@author pzubaha
 *@since 1
 *@version 1
*/
public class Counter {
	/**
	*method add calculate sum of even numbers in range between numbers.
	*@param start - first number
	*@param finish - second number
	*@return result
	*/
	public int add(int start, int finish) {
		if (start > finish) {
			int temp = start;
			start = finish;
			finish = temp;
		}
		if (start % 2 != 0) {
			start++;
		}
		int sum = 0;
		while (start <= finish) {
			sum = sum + start;
			start += 2;
		}
		return sum;
	}
}