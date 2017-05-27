 package ru.pzubaha.start;

import java.util.Scanner;
/**
 * Chapter 2. OOP.
 * Lesson 5. Polymorphism.
 *
 *
 * Solution of task 396.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 17.05.017
 * @version 2
 */
 public class ConsoleInput implements Input {
	/**
	 * Scanner class instance for getting data from console.
	*/
	private Scanner scanner = new Scanner(System.in);
	/**
	 * @param question - question for user.
	 * @return - user inputed data.
	*/
	public String ask(String question) {
		System.out.print(question);
		return scanner.nextLine();

	}
}