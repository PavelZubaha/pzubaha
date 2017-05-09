package ru.pzubaha.start;

import ru.pzubaha.models.Task;

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
	 * main.
	 * @param args - args.
	 */
	public static void main(String[] args) {
		Tracker tracker = new Tracker();
		tracker.add(new Task("New task", "New description"));
		tracker.add(new Task("Second task", "second description"));
	}
}
