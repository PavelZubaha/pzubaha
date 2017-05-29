package ru.pzubaha.start;



import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Chapter 2. OOP.
 * Lesson 4. Encapsulation.
 *
 * Tests for class StubInput check UI menu, through emulating user input.
 * Class contains solution of task 396.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 02.05.017
 * @version 2
 */
public class StubInputTest {

	/**
	* Test add menu.
	*/
	@Test
	public void whenUserAddElementThenTrackerHasIt() {
		//asign tracker and items for test.
		Tracker tracker = new Tracker();
		Input input = new StubInput(new String[]{"1", "1", "Stub task name ", "Stub task desc", "", "0", "0"});
		StartUI startUI = new StartUI(tracker, input);

		//act
		assertThat((tracker.getAll()[0].getName() + tracker.getAll()[0].getDescription()), is("Stub task name Stub task desc"));
	}

	/**
	* Test delete, find by Id menus.
	*/
	@Test
	public void whenUserTryDeleteItemThenTrackerHasNoThisItem() {
		//asign tracker and items for test.
		Tracker tracker = new Tracker();
		Input input = new StubInput(new String[] {
			// Create an Item instance through add menu(1) with the name/description "Stub task name"/"Stub task desc".
			"1", "1",
			"Stub task name", "Stub task desc",

			// Create an Item instance through add menu with the name/description "Second Stub task name "/"Second stub task desc".
			"", "1", "Second Stub task name ", "Second stub task desc",

			// Through find by name menu(6) using the name "Stub task name" get and then copy(1) Id of the first Item instance.
			"", "0", "6", "6", "Stub task name", "1",

			// In delete menu(5), delete first Item through paste(p) copied Id.
			"0", "5", "5", "p", "",

			// Then exit program.
			"0", "0"
			}
		);

		//After emulated user actions check that the second Item instance is on the [0] position in array returned by getAll() method of tracker.
		StartUI startUI = new StartUI(tracker, input);

		//act
		assertThat((tracker.getAll()[0].getName() + tracker.getAll()[0].getDescription()), is("Second Stub task name Second stub task desc"));
	}
	/**
	* Test edit, find by Id menus.
	*/
	@Test
	public void whenUserTryEditItemThenTrackerHasEditedItem() {
		//asign tracker and items for test.
		Tracker tracker = new Tracker();
		Input input = new StubInput(new String[] {
			// Create an Item instance through add menu(1) with the name/description "Stub task name"/"Stub task desc".
			"1", "1",
			"Stub task name", "Stub task desc",

			// Create an Item instance through add menu with the name/description "Second Stub task name "/"Second stub task desc".
			"", "1", "Second Stub task name ", "Second stub task desc",

			// Through find by name menu(6) using the name "Stub task name" get and then copy(1) Id of the first Item instance.
			"", "0", "6", "6", "Stub task name", "1",

			// In edit menu(4), edit first Item through paste(p) copied Id.
			"0", "4", "4", "p", "edited item ", "edited description", "",

			// Then exit program.
			"0", "0"
			}
		);

		//After emulated user actions check that the emulated Item instance is on the [0] position in array returned by getAll() method of tracker.
		StartUI startUI = new StartUI(tracker, input);

		//act
		assertThat((tracker.getAll()[0].getName() + tracker.getAll()[0].getDescription()), is("edited item edited description"));
	}
}