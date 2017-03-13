package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
*Test of class Point.
*@author Pavel Zubaha (Apximar@gmail.com)
*@version 1
*@since 13.03.2017
*/
public class PointTest {
	/**
	*Test method is.
	*case when point is on the line
	*/
	@Test
	public void whenPointIsOnTheLineThenTrue() {
		//asign variable a, b
		Point point = new Point(1, 2);
		//act
		assertThat(point.is(1, 1), is(true));
	}
		/**
	*Test method is.
	*case when point is not on the line
	*/
	@Test
	public void whenPointIsNotOnTheLineThenFalse() {
		//asign variable a, b
		Point point = new Point(2, 2);
		//act
		assertThat(point.is(1, 1), is(false));
	}
}