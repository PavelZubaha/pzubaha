package ru.job4j.calculator;
/**
 *Class Calculator is a solution of task 185.
 *@author pzubaha
 *@since 11.03.2017
 *@version 1
*/
public class Calculator {
	/**
	 * declaration variable wich contents result of calculations.
	 */
	private double result;
	/**
	*method implements arithmetic operation addition.
	*@param first - first number
	*@param second - second number
	*/
	public void add(double first, double second) {
		this.result = first + second;
	}
	/**
	* method implements arithmetic operation substruct.
	*@param first - first number
	*@param second - second number
	*/
	public void substruct(double first, double second) {
		this.result = first - second;
	}
	/**
	* method implements arithmetic operation division.
	*@param first - first number
	*@param second - second number
	*/
	public void div(double first, double second) {
		this.result = first / second;
	}
	/**
	* method implements arithmetic operation multiplication.
	*@param first - first number
	*@param second - second number
	*/
	public void multiple(double first, double second) {
		this.result = first * second;
	}
	/**
	* simply return value of varieble result.
	*@return result of calculation
	*/
	public double getResult() {
	return this.result;
	}
}