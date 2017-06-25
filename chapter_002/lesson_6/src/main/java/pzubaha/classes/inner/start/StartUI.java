package pzubaha.classes.inner.start;


/**
 * Chapter 2. OOP.
 * Lesson 4. Encapsulation.
 *
 * Class Tracker implements issue/request tracker functions.
 * Class contains solution of task 787.
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
		trackerMenu.fillActions();
		do {
			trackerMenu.show();
			String key = input.ask("Select: ");
			trackerMenu.select(key);
		} while (!"y".equals(this.input.ask("Exit (y): ")));
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
