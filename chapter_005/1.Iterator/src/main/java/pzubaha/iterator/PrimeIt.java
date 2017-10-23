package pzubaha.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
public class PrimeIt implements Iterator<Integer> {
    /**
     * Original array.
     */
    private final int[] values;
    /**
     * Position of next prime element if there is exist,
     * otherwise points out of array bounds.
     */
    private int position = 0;

    /**
     * Constructor.
     * @param values original array of int.
     */
    public PrimeIt(int[] values) {
        this.values = values;
    }

    /**
     * Checking has array more even elements.
     * @return true if array has more even elements otherwise false.
     */
    @Override
    public boolean hasNext() {
        setNextPosition();
        return position != values.length;
    }

    /**
     * Next method for getting next prime element.
     * @return next.
     * @throws NoSuchElementException if next called when there is no more prime elements.
     */
    @Override
    public Integer next() {
        int result;
        if (hasNext()) {
            result = values[position];
            position++;
        } else {
            throw new NoSuchElementException();
        }
        return result;
    }

    /**
     * Set next position of prime elements or set position = values.length.
     */
    private void setNextPosition() {
        while (position != values.length) {
           if (isPrime(values[position])) {
               break;
           }
           position++;
        }
    }

    /**
     * Checking value is prime?
     * @param value int value.
     * @return true if value is prime, otherwise false.
     */
    private boolean isPrime(int value) {
        final int CHECK_TO = ((int) Math.sqrt(value));
        boolean result = value > 1 && (value % 2 != 0 || value == 2);
        if (result) {
            for (int i = 3; i <= CHECK_TO; i += 2) {
                if (value % i == 0) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
