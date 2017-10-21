package pzubaha.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Chapter_005. Collection. Pro.
 * Iterator.
 * <p>
 * Contains solution of task 9539.
 * Iterator for two dementia's array.
 * Created 17.10.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class ArrayIterator implements Iterator {
    /**
     * Position of first dementia.
     */
    private int positionFirstDem = 0;
    /**
     * Position of second dementia.
     */
    private int positionSecondDem = 0;
    /**
     * Original values.
     */
    private final int[][] values;

    /**
     * Constructor.
     * @param values original array.
     */
    public ArrayIterator(int[][] values) {
        this.values = values;
    }

    /**
     * has next method for two dementia's array. iterator.
     * @return true value if exists next element.
     */
    @Override
    public boolean hasNext() {
        return values.length > positionFirstDem && values[positionFirstDem].length > positionSecondDem;
    }

    /**
     * method for getting next element of two dementia's array iterator.
     * @return next element.
     */
    @Override
    public Object next() {
        int result;
        if (hasNext()) {
            result = values[positionFirstDem][positionSecondDem];
            if (values[positionFirstDem].length > positionSecondDem + 1) {
                positionSecondDem++;
            } else {
                positionSecondDem = 0;
                positionFirstDem++;
            }
        } else {
            throw new NoSuchElementException();
        }
        return result;
    }
}
