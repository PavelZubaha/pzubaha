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
	*Test max(int first, int second).
	*/
	@Test
	public void whenSecondValueBiggerThanFirstThenReturnSecond() {
		//asign
		Max max = new Max();
		//final double result = max.max(1, 2);
		//act
		assertThat(max.max(1, 2), is(2));
	}
	/**
	*Test max(int first, int second).
	*/
	@Test
	public void whenFirstValueBiggerThanSecondThenReturnFirst() {
		//asign
		Max max = new Max();
		//act
		assertThat(max.max(2, 1), is(2));
	}
	/**
	*Test max(int first, int second, int third).
	*/
	@Test
	public void whenFirstValueIsBiggest() {
		//asign
		Max max = new Max();
		//act
		assertThat(max.max(10, 9, 8), is(10));
	}
	/**
	*Test max(int first, int second, int third).
	*/
	@Test
	public void whenSecondValueIsBiggest() {
		//asign
		Max max = new Max();
		//act
		assertThat(max.max(10, 11, 8), is(11));
	}
	/**
	*Test max(int first, int second, int third).
	*/
	@Test
	public void whenThirdValueIsBiggest() {
		//asign
		Max max = new Max();
		//act
		assertThat(max.max(10, 9, 12), is(12));
	}
}