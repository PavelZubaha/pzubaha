package ru.pzubaha.start;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import ru.pzubaha.models.Item;

/**
 * Test class Tracker.
 *
 * Chapter 2. OOP.
 * Lesson 4. Encapsulation.
 * Solution of task 396.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 08.05.2017
 * @version 2
 */
public class TrackerTest {
	/**
	* Test add and getAll methods.
	*/
	@Test
	public void whenAddNewItemsThenTrackerHasSameItems() {
		//asign tracker and items for test.
		Tracker tracker = new Tracker();
		Item item1 = new Item("test1", "testDescription1");
		Item item2 = new Item("test2", "testDescription2");
		Item[] expected = {item1, item2};

		//act
		tracker.add(item1);
		tracker.add(item2);
		assertThat(tracker.getAll(), is(expected));
	}
	/**
	* Test findById method.
	*/
	@Test
	public void whenFindByIdThenReturnItemWithTheSameId() {
		//asign tracker and items for test.
		Tracker tracker = new Tracker();
		Item item1 = new Item("testName1", "testDescription1");
		Item item2 = new Item("testName2", "testDescription2");
		Item item3 = new Item("testName3", "testDescription3");
		Item item4 = new Item("testName4", "testDescription4");

		//add item into tracker.
		tracker.add(item1);
		tracker.add(item2);
		tracker.add(item3);
		tracker.add(item4);

		String id = new String(item3.getId());
		assertThat(tracker.findById(id), is(item3));
	}
	/**
	* Test findByName method.
	*/
	@Test
	public void whenFindByNameThenReturnArrayOfItemsWithTheSameName() {
		//asign tracker and items for test.
		Tracker tracker = new Tracker();
		Item item1 = new Item("testName1", "testDescription1");
		Item item2 = new Item("name", "testDescription2");
		Item item3 = new Item("name", "testDescription3");
		Item item4 = new Item("testName4", "testDescription4");
		Item[] expected = {item2, item3};

		//add item into tracker.
		tracker.add(item1);
		tracker.add(item2);
		tracker.add(item3);
		tracker.add(item4);

		assertThat(tracker.findByName("name"), is(expected));
	}
	/**
	* Test delete method.
	*/
	@Test
	public void whenDeleteItemsThenTrackerHasNoTheseItems() {
		//asign tracker and items for test.
		Tracker tracker = new Tracker();
		Item item1 = new Item("testName1", "testDescription1");
		Item item2 = new Item("testName2", "testDescription2");
		Item item3 = new Item("testName3", "testDescription3");
		Item item4 = new Item("testName4", "testDescription4");
		Item[] expected = {item2, item4};
		//act
		tracker.add(item1);
		tracker.add(item2);
		tracker.add(item3);
		tracker.add(item4);
		tracker.delete(item3);
		tracker.delete(item1);
		assertThat(tracker.getAll(), is(expected));
	}
	/**
	* Test update method.
	*/
	@Test
	public void whenUpdateItemThenTrackerContainsNewItemInsteadOldOne() {
		//asign tracker and items for test.
		Tracker tracker = new Tracker();
		Item item1 = new Item("testName1", "testDescription1");
		Item item2 = new Item("testName2", "testDescription2");
		Item item3 = new Item("testName3", "testDescription3");
		Item item4 = new Item("testName4", "testDescription4");
		Item itemForUpdate = new Item("updateName", "updateDescription");

		//add item into tracker.
		tracker.add(item1);
		tracker.add(item2);
		tracker.add(item3);
		tracker.add(item4);

		//set id of itemForUpdate equals id of item3.
		itemForUpdate.setId(item3.getId());
		tracker.update(itemForUpdate);

		//expected contains of tracker.
		Item[] expected = {item1, item2, itemForUpdate, item4};
		assertThat(tracker.getAll(), is(expected));
	}
}