package ru.job4j.array;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

/**
 * Part 1. Base syntax.
 * Lesson 5. Test removing duplicates in array.
 * Task 5.3. 225 a program for removing duplicates from array of String
 *
 * @author Zubaha Pavel
 * @version 1
 * @since 19.03.2017
*/
public class ArrayDuplicateTest {
	/**
	*Test case when array has duplicates.
	*/
	@Test
	public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
		ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
		String[] originalStrings = {"Hello, ", "Hello, ", "World", "!", "!", "!", "World", "!", "!", "World", "Hello, "};
		String[] expected = {"Hello, ", "World", "!"};
		assertThat(arrayDuplicate.remove(originalStrings), is(expected));
	}
	/**
	*Test case when array has duplicates.
	*/
	@Test
	public void whenRemoveDuplicatesThenArrayWithoutAllDuplicate() {
		ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
		String[] originalStrings = {"a", "a", "a", "a"};
		String[] expected = {"a"};
		assertThat(arrayDuplicate.remove(originalStrings), is(expected));
	}
}