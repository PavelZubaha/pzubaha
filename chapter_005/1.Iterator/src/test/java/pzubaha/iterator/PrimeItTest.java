package pzubaha.iterator;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Chapter_005. Collection. Pro.
 * Iterator.
 * Iterator of prime numbers.
 * <p>
 * Contains solution of task 151.
 * Class is an Iterator of prime numbers.
 * Created 21.10.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class PrimeItTest {
    /**
     * next and hasNext methods.
     * Case when should be returned prime numbers only.
     */
    @Test(expected = NoSuchElementException.class)
    public void shouldReturnPrimeNumbersOnly() {
        PrimeIt it = new PrimeIt(new int[]{1, 2, 3, 4, 5, 6, 7, 3571});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3571));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    /**
     * next and hasNext methods.
     * that call next doesn't affect on result next method.
     */
    @Test
    public void checkThatHasNextDoesntAffect() {
        PrimeIt it = new PrimeIt(new int[]{2, 3, 4, 5, 6, 7, 12, 3571});
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(7));
        assertThat(it.next(), is(3571));
    }
}