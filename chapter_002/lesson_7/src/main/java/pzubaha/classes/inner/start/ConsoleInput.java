package pzubaha.classes.inner.start;

import java.util.Scanner;
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
    /**
     * method for ask user some key an then check it.
     * @param question - question prompt.
     * @param range - array of valid values.
     * @return return key.
     */
	public int ask(String question, int[] range) {
	    int key = Integer.valueOf(ask(question));
	    boolean exist = false;
	    for (int index : range) {
	        if (index == key) {
	            exist = true;
	            break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Out of menu range");
        }

    }
}