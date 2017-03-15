package ru.job4j.loop;
/**
 *Implementation of pseudographic pyramid.
 *@author pzubaha
 *@since 1
 *@version 1
*/
public class Paint {
	/**
	*realzation of pyramid by height.
	*@param h - height of piramid.
	*@return piramid as a single String.
	*/
	String pyramid(int h) {
		StringBuilder pyramidBuilder = new StringBuilder();
		//Ширина пирамиды.
		int width = h * 2 - 1;
		for (int i = 1; i <= h; i++) {
			for (int j = 1; j <= width; j++) {
				if (j <= (h - i) || j >= (h + i)) {
					pyramidBuilder.append(" ");
				} else {
					pyramidBuilder.append("^");
				}
			}
			if (i < h) {
				pyramidBuilder.append(System.getProperty("line.separator"));
			}
		}
		return pyramidBuilder.toString();
	}
}