package ru.pzubaha.start;


import ru.pzubaha.gui.TrackerMenu;

/**
 * Chapter 2. OOP.
 * Lesson 4. Encapsulation.
 *
 * Class Tracker implements issue/request tracker functions.
 * Class contains solution of task 396.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 02.05.017
 * @version 2
 */
public class StartUI {
	/**
	 * input field.
	 */
	private Input input;
	/**
	 * Constructor.
	 * @param input - initialization input in constructor.
	 */
	public StartUI(Input input) {
		this.input = input;
	}
	/**
	 * init - init needed objects(instances).
	 */
	public void init() {
		Tracker tracker = new Tracker();
		TrackerMenu trackerMenu = new TrackerMenu(this.input, tracker);
		trackerMenu.run();
	}
	/**
	 * main.
	 * @param args - args.
	 */
	public static void main(String[] args) {
		Input input = new ConsoleInput();
		new StartUI(input).init();
	}
}
