package ru.job4j.condition;

import static org.hamcrest.core.Is.is;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;
/**
*Test of class Triangle. task 9461.
*@author Pavel Zubaha (Apximar@gmail.com)
*@version 1
*@since 13.03.2017
*/
public class TriangleTest {
	/**
	*Test method area.
	*case when area is and not equals to zero;
	*/
	@Test
	public void whenTriangleAreaIsReal() {
		//asign vertex  a, b, c and triangle abc;
		Point a = new Point(1, 1);
		Point b = new Point(4, 5);
		Point c = new Point(4, 1);
		Triangle abc = new Triangle(a, b, c);
		double trueArea = 6; //This is a manual calculated value.
		//act
		assertThat(abc.area(), closeTo(trueArea, 0.01));
	}
	/**
	*Test method area.
	*Case when area is equals to zero so triangle is not exists;
	*/
	@Test
	public void whenTriangleIsNotExists() {
		//asign vertex  a, b, c and triangle abc;
		Point a = new Point(1, 1);
		Point b = new Point(2, 2);
		Point c = new Point(3, 3);
		Triangle abc = new Triangle(a, b, c);
		//act
		assertThat(abc.area(), is(-1D));
	}
}