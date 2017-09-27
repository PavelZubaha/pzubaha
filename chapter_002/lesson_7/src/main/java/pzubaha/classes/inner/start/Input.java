package pzubaha.classes.inner.start;
/**
 * Chapter 2. OOP.
 * Lesson 7. Exceptions.
 *
 * Interface for input data.
 * Solution of task 789.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 29.06.17
 * @version 2
 */
public interface Input {
	/**
	 * @param question - question promt.
	 * @return inputed by user data.
	*/
	String ask(String question);
	/**
	 * @param question - question promt.
     * @param range - list of valid values.
	 * @return imputed by user data.
	 */
	int ask(String question, int[] range);
}