package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**.
 *Class CounterTest contents solution of task 192
 *@author pzubaha
 *@since 1
 *@version 1
*/
public class CounterTest {
	/**
	*Test method add(int first, int second).
	*/
	@Test
	public void whenFirstOneAndSecondTenThenThirty() {
		//asign
		Counter counter = new Counter();
		//act
		assertThat(counter.add(1, 10), is(30));
	}
}