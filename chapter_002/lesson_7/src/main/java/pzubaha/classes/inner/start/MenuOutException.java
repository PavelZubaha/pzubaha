package pzubaha.classes.inner.start;

/**
 * Chapter 2. OOP.
 * Lesson 7. Exceptions.
 *
 * Exceptions for some case.
 * Solution of task 787.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 29.05.17
 * @version 2
 */
public class MenuOutException extends RuntimeException {
    /**
     * constructor for handle exception.
     * @param msg - message when it throwed.
     */
    public MenuOutException(String msg) {
        super(msg);
    }
}
