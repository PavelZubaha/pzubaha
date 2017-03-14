package ru.job4j.condition;
/**.
 *Class Point implements solution of the task 188
 *@author pzubaha
 *@since 13.03.2017
 *@version 1
*/
public class Point {
	/**x coordinate of point.*/
	private int x;
	/**y coordinate of point.*/
	private int y;
	/**
	*construct return biggest value of two numbers.
	*@param x - x coordinate,
	*@param y - y coordinate.
	*/
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	*method get x coordinate.
	*@return value x coordinate.
	*/
	public int getX() {
		return this.x;
	}
	/**
	*method return x coordinate.
	*@return value y coordinate.
	*/
	public int getY() {
		return this.y;
	}
	/**
	*method return condition of the point.
	*is point lean on the line y(x) = a * x + b ?
	*@param a - a coefficient of the equation,
	*@param b - b coefficient of the equation,
	*@return true or false.
	*/
	public boolean is(int a, int b) {
			return (a * this.x + b) == this.y;
	}
}