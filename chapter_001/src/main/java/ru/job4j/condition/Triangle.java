package ru.job4j.condition;
/**.
 *Class Triangle implements solution of the task 9461
 *@author pzubaha
 *@since 13.03.2017
 *@version 1
*/
public class Triangle {
	/**
	Field - first point of triangle.
	*/
	private Point a;
	/**
	Field - second point of triangle.
	*/
	private Point b;
	/**
	Field - third point of triangle.
	*/
	private Point c;
	/**
	*construct initiation.
	*@param a - First point of triangle.
	*@param b - Second point of triangle.
	*@param c - Third point of triangle.
	*/
	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	/**
	*method calculates triangle area.
	*@return area of triangle by Heron's formula https://en.wikipedia.org/wiki/Heron%27s_formula.
	*/
	public double area() {
		/**Field - calculation quadrated length ab side .*/
		 double abQuadrate = (a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY());
		/**Field - calculation quadrated length bc side .*/
		 double bcQuadrate = (b.getX() - c.getX()) * (b.getX() - c.getX()) + (b.getY() - c.getY()) * (b.getY() - c.getY());
		/**Field - calculation quadrated length ca side .*/
		 double caQuadrate = (c.getX() - a.getX()) * (c.getX() - a.getX()) + (c.getY() - a.getY()) * (c.getY() - a.getY());
		 double area = Math.sqrt((4 * abQuadrate * bcQuadrate - (abQuadrate + bcQuadrate - caQuadrate) * (abQuadrate + bcQuadrate - caQuadrate))) / 4;
		 return ((area > 0.01) ? area : -1D);
	}
}