package pzubaha.classes.inner.start;


/**
 * Chapter 2. OOP.
 * Lesson 7. Exceptions.
 *
 * Ask user some question and check it.
 * Solution of task 787.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 17.05.17
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
		int[] range = trackerMenu.fillActions();
		do {
			trackerMenu.show();
			trackerMenu.select(input.ask("Select: ", range));
		} while (!"y".equals(this.input.ask("Exit (y): ")));
	}
	/**
	 * main.
	 * @param args - args.
	 */
	public static void main(String[] args) {
		Input input = new ValidateInput();
		new StartUI(input).init();
	}
}
