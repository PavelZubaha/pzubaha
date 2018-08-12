package pzubaha.classes.inner.start;

import pzubaha.classes.inner.models.Category;
import pzubaha.classes.inner.models.Comment;
import pzubaha.classes.inner.models.Item;
import pzubaha.classes.inner.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Chapter 2. OOP.
 * Lesson 7. Exception.1
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
	 * Current user.
	 */
	private User curUser;

	/**
	 * Getter for current user.
	 * @return current user instance.
	 */
	public User getCurUser() {
		return curUser;
	}

	/**
	 * Setter for current user.
	 * @param curUser current user instance.
	 */
	public void setCurUser(User curUser) {
		this.curUser = curUser;
	}

	public Tracker() {
	}
	/**
	 * method for add new item example.
	 * @param item - item.
	 */
	public void add(Item item) {
		item.setId(Integer.parseInt(this.generateId()));
		this.items.add(item);
	}
	/**
	 * method for searching item example.
	 * @param id - id of item that need to find.
	 * @return the item with unique id or null.
	 */
	public Item findById(int id) {
		Item result = null;
		for (Item item : this.items) {
			if (item != null && id == item.getId()) {
				result = item;
				break;
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
				if (this.items.get(index).getId() == item.getId()) {
                    for (Comment comment : this.items.get(index).getComments()) {
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

	public User getUserByLoginAndPass(String login, String pass) {
		return null;
	}
	public List<Category> getCategories() {
		return new ArrayList<>();
	}

	public void addComment(Comment comment) {
		findById(comment.getItemId()).addComment(comment);
	}

	public List<Comment> getComments(Item item) {
		return new ArrayList<>(item.getComments());
	}

	public void delComment(Comment comment) {
		findById(comment.getItemId()).delComment(comment.getCommentId());
	}
}