package ru.pzubaha;
/**
 * Chapter 2. OOP.
 * Lesson 4. Polymorphism.
 *
 * Class Paint for painting the shape.
 * Class contains solution of task 785.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 30.05.017
 * @version 1
*/
public class Paint {
	/**
	* method for printing defferent shapes.
	* @param shape - shape interface.
	*/
	public void draw(Shape shape) {
		System.out.println(shape.pic());
	}
}