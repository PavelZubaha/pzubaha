package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Test.
 *@author pzubaha
 *@since 1
 *@version 1
*/
public class PaintTest {
	/**.
	*Test pyramid pseudographic
	*/
	@Test
	public void whenHeightOfPyramidIsTwo() {
		//asign
		Paint objectPyramid = new Paint();
		//act
		String lineSeparator = System.getProperty("line.separator");
		String result = objectPyramid.pyramid(2);
		String expected = String.format(" ^ %s^^^", lineSeparator);
		assertThat(expected, is(result));
	}
	/**.
	*Test pyramid pseudographic
	*/
	@Test
	public void whenHeightOfPyramidIsFour() {
		//asign
		Paint objectPyramid = new Paint();
		//act
		String lineSeparator = System.getProperty("line.separator");
		String result = objectPyramid.pyramid(4);
		String expected = String.format("   ^   %s  ^^^  %s ^^^^^ %s^^^^^^^", lineSeparator, lineSeparator, lineSeparator);
		assertThat(expected, is(result));
	}
}