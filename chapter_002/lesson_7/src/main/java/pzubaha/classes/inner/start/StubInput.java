package pzubaha.classes.inner.start;

/**
 * Chapter 2. OOP.
 * Lesson 7. Exceptions.
 *
 * Class for testing input.
 * Solution of task 789.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 29.06.17
 * @version 2
 */
 public class StubInput implements Input {
	/**
	 * An array of user responses for automatic testing.
	*/
	private String[] answers;
	/**
	 * counter.
	*/
	private int position = 0;
	/**
	 * Constructor.
	 * @param answers - array of simulated user answers for testing automation
	*/
	public StubInput(String[] answers) {
		this.answers = answers;
	}
	/**
	 * for testing input.
	 * @param question - simulated user answers for testing automation.
	 * @return instance of user answer.
	*/
	public String ask(String question) {
		return this.answers[position++];
	}
	/**
	 * for testing input.
	 * @param question - simulated user answers for testing automation.
	 * @param range - an array of valid values.
	 * @return instance of user answer.
	 */
	public int ask(String question, int[] range) {
		//throw new UnsupportedOperationException("Unsupported operation");
        return -1;
	}

 }