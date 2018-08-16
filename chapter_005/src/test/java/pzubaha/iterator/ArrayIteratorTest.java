package pzubaha.iterator;

import org.junit.Test;

import java.util.NoSuchElementException;

import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Chapter_005. Collection. Pro.
 * Iterator.
 * <p>
 * Contains solution of task 9539.
 * Test Iterator for two dementia's array.
 * Created 17.10.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class ArrayIteratorTest {
    /**
     * test hasnext() method.
     * @throws NoSuchElementException throw when there is no next elements.
     */
    @Test
    public void whenCallHasNextThenReturnTrueIfPointerHasItOrFalse() throws NoSuchElementException {
        //arrange
        ArrayIterator it = new ArrayIterator(new int[][] {{1}});
        String expected = String.valueOf("true, true, false");

        //act
        StringBuilder sb = new StringBuilder();
        sb.append(format("%s, %s, ", it.hasNext(), it.hasNext()));
        it.next();
        sb.append(it.hasNext());
        String result = sb.toString();

        //assert
        assertThat(expected, is(result));
    }

    /**
     * test next method.
     * @throws NoSuchElementException throw when there is no next elements.
     */
    @Test
    public void whenCallNextThenReturnedNextPointedElement() throws NoSuchElementException {
        //arrange
        ArrayIterator it = new ArrayIterator(new int[][] {{1, 2}, {3}});
        String expected = String.valueOf("1, 2, 3");

        //act
        String result = format("%s, %s, %s", it.next(), it.next(), it.next());
        //assert
        assertThat(expected, is(result));
    }
}