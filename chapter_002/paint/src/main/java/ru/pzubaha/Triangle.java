package ru.pzubaha;
/**
 * Chapter 2. OOP.
 * Lesson 4. Polymorphism.
 *
 * Class Triangle for getting triangle as instance of String.
 * Class contains solution of task 785.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 30.05.017
 * @version 1
*/
public class Triangle implements Shape {
	/**
	* method for building triangle as String instance.
	* @return triangle as String interface.
	*/
	@Override
	public String pic() {
		String separator = System.getProperty("line.separator");
		StringBuilder result = new StringBuilder();
		result.append(separator);
		for (int index = 0; index < 10; index++) {
			for (int i = 0; i <= index; i++) {
				result.append("U");
			}
			result.append(separator);
		}
		return result.toString();
	}
}