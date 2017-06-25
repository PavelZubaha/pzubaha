package pzubaha.classes.inner.start;

/**
 * Chapter 2. OOP.
 * Lesson 5. Polymorphism.
 *
 * Solution of task 787.
 * Created for making test.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 17.05.017
 * @version 2
 */
public class StartUITest {
	/**
	 * main.
	 * @param args - args.
	*/
	public static void main(String[] args) {
		Input input = new StubInput(new String[] {"create stub task", "stub desc"});
		new StartUI(input).init();
	}
}