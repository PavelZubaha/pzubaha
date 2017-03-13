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
		final double result max.max(1,2);
		//act
		assertThat(result, is(2));
	}
	/**
	*Test max.
	*/
	@Test
	public void whenFirstValueBiggerThanSecondThenReturnFirst() {
		//asign
		Max max = new Max();
		final int result max.max(2,1);
		//act
		assertThat(result, is(2));
	}
}