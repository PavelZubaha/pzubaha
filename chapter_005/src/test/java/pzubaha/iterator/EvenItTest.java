package pzubaha.iterator;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Chapter_005. Collection. Pro.
 * Iterator.
 *
 * <p>
 * Contains solution of task 150.
 * Class for testing even iterator
 * Created 21.10.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class EvenItTest {
    /**
     * Testing next and has next methods.
     */
    @Test(expected = NoSuchElementException.class)
    public void hasNextShouldReturnEvenNumbersSequentially()  {
        EvenIt it = new EvenIt(new int[]{1, 2, 3, 4, 5, 6, 7});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    /**
     * Testing next and has next methods.
     * Proving that calling hasNext doesn't affect on result of next method.
     */
    @Test public void checkThatHasNextDoesntAffect() {
        EvenIt it = new EvenIt(new int[]{1, 2, 4, 6});
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(6));
    }
}