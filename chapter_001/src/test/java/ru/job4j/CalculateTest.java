package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
*Test/
*@author Pavel Zubaha (Apximar@gmail.com)
*@version $Id$
*@since 0.1
*/
public class CalculateTest {
	/**
	*Test add.
	*/
	@Test
	public void whenAddOneToOneThenTwo() {
		//asign
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut (new PrintStream(out));
		Calculate.main (null);
		//act
		
		assertThat(
			out.toString(),
			is(
				String.format(
					"Hello Word%s",
					System.getProperty("line.separator")
				)
			)
		);
	}
}