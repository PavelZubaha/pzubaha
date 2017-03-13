package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
*Test.
*@author Pavel Zubaha (Apximar@gmail.com)
*@version 1
*@since 1
*/
public class MaxTest {
	/**
	*Test max.
	*/
	@Test
	public void whenSecondValueBiggerThanFirstThenReturnSecond() {
		//asign
		Max max = new Max();
		//final double result = max.max(1, 2);
		//act
		assertThat(max.max(1, 2), is(2D));
	}
	/**
	*Test max.
	*/
	@Test
	public void whenFirstValueBiggerThanSecondThenReturnFirst() {
		//asign
		Max max = new Max();
		//final double result = max.max(2, 1);
		//act
		assertThat(max.max(2, 1), is(2D));
	}
}