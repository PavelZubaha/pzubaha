package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**.
 *Class FactorialTest contents tests for class Factorial.
 *@author pzubaha
 *@since 1
 *@version 1
*/
public class FactorialTest {
	/**
	*Test method calc from Class Factorial.
	*/
	@Test
	public void whenValueIsFiveThenResultIsOneTwoZero() {
		//asign
		Factorial factorial = new Factorial();
		//act
		assertThat(factorial.calc(5), is(120D));
	}
	/**
	*Test method calc from Class Factorial.
	*/
	@Test
	public void whenValueIsZeroThenFactorialIsOne() {
		//asign
		Factorial factorial = new Factorial();
		//act
		assertThat(factorial.calc(0), is(1D));
	}
}