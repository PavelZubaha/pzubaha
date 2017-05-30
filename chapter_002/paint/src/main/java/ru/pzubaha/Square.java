package ru.pzubaha;
/**
 * Chapter 2. OOP.
 * Lesson 4. Polymorphism.
 *
 * Class Square for getting square as instance of String.
 * Class contains solution of task 785.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 30.05.017
 * @version 1
*/
public class Square implements Shape {
	/**
	* method for building square as String instance.
	* @return square as String interface.
	*/
	@Override
	public String pic() {
		String separator = System.getProperty("line.separator");
		StringBuilder result = new StringBuilder();
		result.append(separator);
		StringBuilder oneStringOfSquare = new StringBuilder();
		for (int i = 0; i < 12; i++) {
				oneStringOfSquare.append("U");
			}
			oneStringOfSquare.append(separator);
		for (int index = 0; index < 8; index++) {
			result.append(oneStringOfSquare);
		}
		return result.toString();
	}
}