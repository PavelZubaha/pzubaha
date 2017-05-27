package ru.pzubaha.models;
/**
 * Chapter 2. OOP.
 * Lesson 4. Encapsulation.
 *
 * Class Task implements task type of tracker issue/request.
 * Class contains solution of task 396.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 02.05.017
 * @version 2
 */
public class Task extends Item {
	/**
	 * Constructor for Task.
	 * @param name - name.
	 * @param desc - description.
	 */
	public Task(String name, String desc) {
		super(name, desc);
	}
}