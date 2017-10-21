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
        setNextPosition();
    }

    /**
     * Checking has array more even elements.
     * @return true if array has more even elements otherwise false.
     */
    @Override
    public boolean hasNext() {
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
            this.setNextPosition();
        } else {
            throw new NoSuchElementException();
        }
        return result;
    }

    /**
     * Set next position of prime elements or set position = values.length.
     */
    private void setNextPosition() {
        for (; position != values.length; position++) {
           if (isPrime(values[position])) {
               break;
           }
        }
    }

    /**
     * Checking value is prime?
     * @param value int value.
     * @return true if value is prime, otherwise false.
     */
    private boolean isPrime(int value) {
        boolean result = true;
        if (value < 2) {
            result = false;
        } else {
            for (int i = 2; i < value; i++) {
                if (value % i == 0) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
