package pzubaha.classes.inner.start;
/**
 * Chapter 2. OOP.
 * Lesson 5. Polymorphism.
 *
 * Interface for input data.
 * Solution of task 787.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 17.05.017
 * @version 2
 */
public interface Input {
	/**
	 * @param question - question promt.
	 * @return inputed by user data.
	*/
	String ask(String question);
}