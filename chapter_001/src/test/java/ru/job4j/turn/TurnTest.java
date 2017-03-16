package ru.job4j.turn;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**Test array inverting.
 *Solution on task 4441.
 *@author Zubaha Pavel.
 *@since 16.03.17
 *@version 1
*/
public class TurnTest {
	/**
	 *Test case when array length is paired.
	*/
	@Test
	public void whenArrayIsPaired() {
		//asign
		Turn turn = new Turn();
		int[] array = new int[]{1, 2, 3, 4, 5, 6};
		int[] expectedArray = new int[]{6, 5, 4, 3, 2, 1};
		assertThat(turn.back(array), is(expectedArray));
	}
	/**
	 *Test case when array length is not paired.
	*/
	@Test
	public void whenArrayIsNotPaired() {
		//asign
		Turn turn = new Turn();
		int[] array = new int[]{2, 3, 4, 5, 6};
		int[] expectedArray = new int[]{6, 5, 4, 3, 2};
		assertThat(turn.back(array), is(expectedArray));
	}
}