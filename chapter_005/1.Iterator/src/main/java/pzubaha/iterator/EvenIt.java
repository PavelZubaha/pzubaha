package pzubaha.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Chapter_005. Collection. Pro.
 * Iterator.
 *
 * <p>
 * Contains solution of task 150.
 * Class contains even numbers Iterator.
 * Created 20.10.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class EvenIt implements Iterator {
    /**
     * original array of int.
     */
    private int[] numbers;
    /**
     * index of next even element of array. If there is no element position = numbers.length.
     */
    private int position = 0;

    /**
     * Constructor.
     * @param numbers original array.
     */
    public EvenIt(int[] numbers) {
        this.numbers = numbers;
        setNextEvenPointer();
    }

    /**
     * checking has array more even elements.
     * @return true if array has more even elements.
     */
    @Override
    public boolean hasNext() {
        return position < numbers.length;
    }

    /**
     * getting next even element.
     * @return value of next even element if it does exist.
     * otherwise trow NoSuchElementException
     */
    @Override
    public Integer next() {
        int result;
        if (hasNext()) {
            result = numbers[position];
            position++;
            setNextEvenPointer();
        } else {
            throw new NoSuchElementException();
        }
        return result;
    }

    /**
     * setting next even pointer.
     */
    private void setNextEvenPointer() {
        for (; position < numbers.length; position++) {
            if (numbers[position] % 2 == 0) {
                break;
            }
        }
    }
}
