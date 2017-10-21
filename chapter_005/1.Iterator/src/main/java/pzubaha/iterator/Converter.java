package pzubaha.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Chapter_005. Collection. Pro.
 * Iterator.
 * Converter.
 * <p>
 * Contains solution of task 152.
 * Class implements Iterator<Iterators<Integer>>
 * Created 21.10.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class Converter {
    /**
     * method for converting iterator of iterators to simple iterator.
     * @param it Iterator of iterators<Integer>.
     * @return Iterator<Iterator>
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            private Iterator<Integer> iter;
            {
                setNextPointer();
            }
            @Override
            public boolean hasNext() {
                boolean result = false;
                if (iter.hasNext()) {
                    result = true;
                } else {
                    setNextPointer();
                    if (iter.hasNext()) {
                        result = true;
                    }
                }
                return result;
            }

            @Override
            public Integer next() {
                int value;
                if (hasNext()) {
                    value = iter.next();
                } else {
                    throw new NoSuchElementException();
                }
                return value;
            }
            private void setNextPointer() {
                if (it.hasNext()) {
                    iter = it.next();
                }
            }
        };
    }
}
