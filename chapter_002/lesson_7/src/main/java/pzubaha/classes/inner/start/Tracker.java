package pzubaha.classes.inner.start;


import pzubaha.classes.inner.models.Item;

import java.util.ArrayList;
import java.util.List;
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
	 * List of items.
	 */
	private ArrayList<Item> items = new ArrayList<>(100);

	/**
	 * additional field for generating id.
	 */
	private static final Random RN = new Random();
	/**
	 * method for add new item example.
	 * @param item - item.
	 */
	public void add(Item item) {
		item.setId(this.generateId());
		this.items.add(item);
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
	public List<Item> getAll() {
		List<Item> result = new ArrayList<>();
        result.addAll(items);
		return result;
	}

	/**
	 * method for update item.
	 * @param item - item which need to update.
	 */
	public void editItem(Item item) {
		if (item != null) {
			for (int index = 0; index != items.size(); index++) {
				if (this.items.get(index).getId().equals(item.getId())) {
                    for (String comment : this.items.get(index).getComments()) {
                        item.addComment(comment);
                    }
                    this.items.set(index, item);
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
			this.items.remove(item);
		}
	}

	/**
	 * method for searching item by name.
	 * @param name - name of needed item.
	 * @return the array of items with the same name. When there are no items with the needed name return empty array "[]".
	 */
	public List<Item> findByName(String name) {
		List<Item> result = new ArrayList<>();
		if (name != null) {
			for (int index = 0; index != items.size(); index++) {
				if (this.items.get(index).getName().equals(name)) {
					result.add(this.items.get(index));
				}
			}
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