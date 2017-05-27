package ru.pzubaha.start;

/**
 * Chapter 2. OOP.
 * Lesson 5. Polymorphism.
 *
 * Class for testing input.
 * Solution of task 396.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 17.05.017
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
 }