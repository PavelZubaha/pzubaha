package ru.pzubaha;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Chapter 2. OOP.
 * Lesson 4. Polymorphism.
 * <p>
 * Test class.
 * Class contains solution of task 785.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 * @since 30.05.017
 */
public class PaintTest {
    /**
     * Test draw triangle.
     */
    @Test
    public void whenDrawTriangleThenOutTriangleAsString() {
        //asign output stream.
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        //define expected triangle as the String.
        StringBuilder expectedBuilder = new StringBuilder();
        String separator = System.getProperty("line.separator");
        expectedBuilder.append(separator);
        for (int index = 0; index < 10; index++) {
            for (int i = 0; i <= index; i++) {
                expectedBuilder.append("U");
            }
            expectedBuilder.append(separator);
        }
        expectedBuilder.append(separator);
        String expectedString = expectedBuilder.toString();

        Paint paint = new Paint();
        paint.draw(new Triangle());
        //checking.
        assertThat(out.toString(), is(expectedString));
    }

    /**
     * Test draw square.
     */
    @Test
    public void whenDrawSquareThenOutSquareAsString() {
        //asign output stream.
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));


        //define expected square as the String.
        StringBuilder expectedBuilder = new StringBuilder();
        String separator = System.getProperty("line.separator");
        expectedBuilder.append(separator);
        StringBuilder oneStringOfSquare = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            oneStringOfSquare.append("U");
        }
        oneStringOfSquare.append(separator);
        for (int index = 0; index < 8; index++) {
            expectedBuilder.append(oneStringOfSquare);
        }
        expectedBuilder.append(separator);
        String expectedString = expectedBuilder.toString();

        //act drawing.
        Paint paint = new Paint();
        paint.draw(new Square());
        //checking.
        assertThat(out.toString(), is(expectedString));
    }
}