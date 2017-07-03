package pzubaha.classes.inner.start;


import pzubaha.classes.inner.models.Item;

import java.util.Random;
/**
 * Chapter 2. OOP.
 * Lesson 7. Exception.
 *
 * Class Tracker implements issue/request tracker functions.
 * Class contains solution of task 789.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 29.06.17
 * @version 2
 */
public class Tracker {
	/**
	 * array of items.
	 */
	private Item[] items = new Item[100];
	/**
	 * current amount of items.
	 */
	private int position = 0;
	/**
	 * additinal field for generate id.
	 */
	private static final Random RN = new Random();
	/**
	 * method for add new item example.
	 * @param item - item.
	 */
	public void add(Item item) {
		item.setId(this.generateId());
		this.items[position++] = item;
	}
	/**
	 * method for searching item example.
	 * @param id - id of item that need to find.
	 * @return the item with unique id or null.
	 */
	public Item findById(String id) {
		Item result = null;
		if (id != null) {
			for (Item item : this.items) {
				if (item != null && id.equals(item.getId())) {
					result = item;
					break;
				}
			}
		}
		return result;
	}
	/**
	 * method for generate id.
	 * @return the id.
	 */
	private String generateId() {
		return String.valueOf(System.currentTimeMillis() + RN.nextInt(100));
	}
	/**
	 * method for get all items.
	 * @return array of items.
	 */
	public Item[] getAll() {
		Item[] result = new Item[this.position];
		for (int index = 0; index != this.position; index++) {
			result[index] = this.items[index];
		}
		return result;
	}
	/**
	 * method for update item.
	 * @param item - item which need to update.
	 */
	public void editItem(Item item) {
		if (item != null) {
			for (int index = 0; index != this.position; index++) {
				if (this.items[index].getId().equals(item.getId())) {
                    for (String comment : this.items[index].getComments()) {
                        item.addComment(comment);
                    }
                    this.items[index] = item;
					break;
				}
			}
		}
	}
	/**
	 * method for delete item.
	 * @param item - item which need to delete.
	 */
	public void delete(Item item) {
		if (item != null) {
			for (int index = 0; index != this.position; index++) {
				if (item.getId().equals(this.items[index].getId())) {
					this.position--;
					System.arraycopy(this.items, (index + 1), this.items, index, (this.position - index));
					this.items[this.position] = null;
					break;
				}
			}
		}
	}
	/**
	 * method for searching item by name.
	 * @param name - name of needed item.
	 * @return the array of items with the same name. When there are no items with the needed name return empty array "[]".
	 */
	public Item[] findByName(String name) {
		Item[] resultWhithNulls = new Item[position];
		int resultLength = 0;
		if (name != null) {
			for (int index = 0; index != this.position; index++) {
				if (name.equals(this.items[index].getName())) {
					resultWhithNulls[resultLength] = this.items[index];
					resultLength++;
				}
			}
		}
		Item[] result = new Item[resultLength];
		if (resultLength != 0) {
			System.arraycopy(resultWhithNulls, 0, result, 0, resultLength);
		}
		return result;
	}

    /**
     * Exit method.
     */
	public void exit() {
	    System.exit(0);
    }
}