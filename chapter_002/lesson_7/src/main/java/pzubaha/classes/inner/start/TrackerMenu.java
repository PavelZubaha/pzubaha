package pzubaha.classes.inner.start;

import pzubaha.classes.inner.models.Category;
import pzubaha.classes.inner.models.Item;
import pzubaha.classes.inner.templates.BaseAction;

import java.util.*;

/**
 * Chapter 2. OOP.
 * Lesson 7. Exceptions.
 *
 * Class managing tracker through command line.
 * Class contains solution of task 789.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 29.06.17
 * @version 2
*/
public class TrackerMenu {
	/**
	 * Field for input interface.
	 */
	private Input input;
	/**
	 * Field for tracker reference.
	 */
	private Tracker tracker;
	/**
	 * Array contains different user select ions.
	 */
	private List<UserAction> actions = new ArrayList<>();
	/**
	 * Constructor.
	 * @param input - reference to the input instance.
	 * @param tracker - reference to the tracker instance.
	 */
	public TrackerMenu(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}

	/**
	 * For filling user different actions.
	 */
	public void fillActions() {
		actions.addAll(Arrays.asList(
				new AddItem("Add new item", 0),
				new TrackerMenu.ShowAll("Show all items", 1),
				new EditItem("Edit Item", 2),
				new AddComments("Add comment to Item", 3),
				new ShowComments("Show comments", 4),
				new DelItem("Delete item", 5),
				new TrackerMenu.FindItemById("Find item by the Id", 6),
				new FindItemByName("Find item by the name", 7),
				new DelComments("Delete comments", 8)
				));
	}
	public void delAction(UserAction userAction) {
		actions.remove(userAction);
	}
    /**
     * Get action range.
     * @return - array of integer values of action.
     */
	public int[] getActionRange() {
	    int[] result = new int[this.actions.size()];
	    for (int index = 0; index < actions.size(); index++) {
            result[index] = actions.get(index).key();
        }
        return result;
    }
    /**
     * Add new action.
     * @param action - userAction instance.
     */
    public void addAction(UserAction action) {
        this.actions.add(action);
    }
	/**
	 * Showing menu.
	 */
	public void show() {
		if (tracker.getCurUser() != null) {
			System.out.printf("Hello, %s%n", tracker.getCurUser().getUserName());
		}
		actions.sort(Comparator.comparingInt(UserAction::key));
		for (int i = 0; i < actions.size(); i++) {
			if (actions.get(i) != null) {
				System.out.println(actions.get(i).info());
			}
		}
	}

	/**
	 * Perform action selected by user.
	 * @param key - index of action.
	 */
	public void select(int key) {
		for (int i = 0; i != actions.size(); i++) {
			if (actions.get(i).key() == key) {
				actions.get(i).execute(input, tracker);
				break;
			}
		}
	}

	/**
	 * Chapter 2. OOP.
	 * Lesson 4. Polymorphism.
	 *
	 * Class managing tracker through command line.
	 * Class contains solution of task 787.
	 *
	 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
	 * @since 25.06.17
	 * @version 2
	 */
	private class AddItem extends BaseAction {
		/**
		 * Constructor for action.
		 *
		 * @param name - action name.
		 * @param key  - action string.
		 */
		public AddItem(String name, int key) {
			super(name, key);
		}

		/**
		 * action for each menu item.
		 * @param input - reference to input instance.
		 * @param tracker - reference to tracker instance.
		 */
		public void execute(Input input, Tracker tracker) {
			String name = input.ask("Please enter the task name: ");
			String desc = input.ask("Please enter the task description: ");
			int cat = askCategory();
			tracker.add(new Item(name, desc, tracker.getCurUser().getUserId(), cat));
			input.ask("Click Enter to continue: ");
		}
		private int askCategory() {
			List<Category> categories = tracker.getCategories();
			int[] range = new int[categories.size()];
			StringBuilder sb = new StringBuilder(String.format("Chose category from list%n"));
			Category cat = null;
			for (int i = 0; i != range.length; i++) {
				cat = categories.get(i);
				sb.append(String.format("%d. %s%n", i, cat.getName()));
				range[i] = i;
			}
			cat = categories.get(input.ask(sb.toString(), range));
			return cat.getCatId();
		}
	}
	/**
	 * Chapter 2. OOP.
	 * Lesson 4. Polymorphism.
	 *
	 * Class managing tracker through command line.
	 * Class contains solution of task 787.
	 *
	 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
	 * @since 25.06.17
	 * @version 2
	 */
	private static class ShowAll extends BaseAction {

		/**
		 * Constructor for action.
		 *
		 * @param name - action name.
		 * @param key  - action string.
		 */
		public ShowAll(String name, int key) {
			super(name, key);
		}

		/**
		 * action for each menu item.
		 * @param input - reference to input instance.
		 * @param tracker - reference to tracker instance.
		 */
		public void execute(Input input, Tracker tracker) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(String.format("The list of found Items: %n%-10s%-15s%-20s%-16s", "№", "Name", "Description", "Id"));
			int index = 1;
			for (Item item : tracker.getAll()) {
				stringBuilder.append(String.format("%n%d%-6s %-14s %-18s %-14s", index++, ".", item.getName(), item.getDescription(), item.getId()));
			}
            input.ask(String.format("%s%n%s", stringBuilder.toString(), "Click Enter to continue: "));
		}
	}
	/**
	 * Chapter 2. OOP.
	 * Lesson 4. Polymorphism.
	 *
	 * Class managing tracker through command line.
	 * Class contains solution of task 787.
	 *
	 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
	 * @since 25.06.17
	 * @version 2
	 */
	private static class FindItemById extends BaseAction {

		/**
		 * Constructor for action.
		 *
		 * @param name - action name.
		 * @param key  - action string.
		 */
		public FindItemById(String name, int key) {
			super(name, key);
		}
		/**
		 * action for each menu item.
		 * @param input - reference to input instance.
		 * @param tracker - reference to tracker instance.
		 */
		public void execute(Input input, Tracker tracker) {
			Item foundItem = getItem(input, tracker);
			if (foundItem != null) {
				input.ask(String.format("Name: %s%nDescription: %s%nItem Id: %s%n Comments: %n%s", foundItem.getName(), foundItem.getDescription(), foundItem.getId(), foundItem.showItemComments()));
			} else {
				input.ask("There is no item with the Id");
			}
		}
	}
}
/**
 * Chapter 2. OOP.
 * Lesson 4. Polymorphism.
 *
 * Class managing tracker through command line.
 * Class contains solution of task 787.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 25.06.17
 * @version 2
 */
class EditItem extends BaseAction {

	/**
	 * Constructor for action.
	 *
	 * @param name - action name.
	 * @param key  - action string.
	 */
	public EditItem(String name, int key) {
		super(name, key);
	}

	/**
	 * action for each menu item.
	 *
	 * @param input   - reference to input instance.
	 * @param tracker - reference to tracker instance.
	 */
	public void execute(Input input, Tracker tracker) {
		Item foundItem = getItem(input, tracker);
		if (foundItem != null) {
			String name = input.ask("Enter new name of item: ");
			String desc = input.ask("Enter new description: ");
			Item newItem = new Item(name, desc, tracker.getCurUser().getUserId(), foundItem.getCatId());
			newItem.setId(foundItem.getId());
			tracker.editItem(newItem);
			input.ask("Item edited");
		} else {
			input.ask("There is no item with the Id");
		}
	}
}

/**
 * Chapter 2. OOP.
 * Lesson 4. Polymorphism.
 * <p>
 * Class managing tracker through command line.
 * Class contains solution of task 787.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 2
 * @since 25.06.17
 */
class AddComments extends BaseAction  {

	/**
	 * Constructor for action.
	 *
	 * @param name - action name.
	 * @param key  - action string.
	 */
	public AddComments(String name, int key) {
		super(name, key);
	}

	/**
	 * action for each menu item.
	 *
	 * @param input   - reference to input instance.
	 * @param tracker - reference to tracker instance.
	 */
	public void execute(Input input, Tracker tracker) {
		Item foundItem = getItem(input, tracker);
		if (foundItem != null) {
			String comment = input.ask("Enter comment to add: ");
			foundItem.addComment(comment);
			input.ask("Comment added");
		} else {
			input.ask("There is no item with the Id");
		}
	}
}

/**
 * Chapter 2. OOP.
 * Lesson 4. Polymorphism.
 * <p>
 * Class managing tracker through command line.
 * Class contains solution of task 787.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 2
 * @since 25.06.17
 */
class ShowComments extends BaseAction {

	/**
	 * Constructor for action.
	 *
	 * @param name - action name.
	 * @param key  - action string.
	 */
	public ShowComments(String name, int key) {
		super(name, key);
	}

	/**
	 * action for each menu item.
	 *
	 * @param input   - reference to input instance.
	 * @param tracker - reference to tracker instance.
	 */
	public void execute(Input input, Tracker tracker) {
		Item foundItem = getItem(input, tracker);
		if (foundItem != null) {
			String stringComments = foundItem.showItemComments();
			if (stringComments.length() > 0) {
				input.ask(stringComments);
			} else {
				input.ask("This item has no comments.");
			}
		} else {
			input.ask("There is no item with the Id");
		}
	}
}

/**
 * Chapter 2. OOP.
 * Lesson 7. Exceptions.
 * <p>
 * Class managing tracker through command line.
 * Class contains solution of task 787.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 2
 * @since 25.06.17
 */
class DelItem extends BaseAction {

	/**
	 * Constructor for action.
	 *
	 * @param name - action name.
	 * @param key  - action string.
	 */
	public DelItem(String name, int key) {
		super(name, key);
	}

	/**
	 * action for each menu item.
	 *
	 * @param input   - reference to input instance.
	 * @param tracker - reference to tracker instance.
	 */
	public void execute(Input input, Tracker tracker) {
		Item foundItem = getItem(input, tracker);
		if (foundItem != null) {
			tracker.delete(foundItem);
			input.ask("Item deleted");
		} else {
			input.ask("There is no item with the Id");
		}
	}
}

/**
 * Chapter 2. OOP.
 * Lesson 7. Exceptions.
 * <p>
 * Class managing tracker through command line.
 * Class contains solution of task 787.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 2
 * @since 25.06.17
 */
class FindItemByName extends BaseAction {

	/**
	 * Constructor for action.
	 *
	 * @param name - action name.
	 * @param key  - action string.
	 */
	public FindItemByName(String name, int key) {
		super(name, key);
	}

	/**
	 * action for each menu item.
	 *
	 * @param input   - reference to input instance.
	 * @param tracker - reference to tracker instance.
	 */
	public void execute(Input input, Tracker tracker) {
		String name = input.ask("Enter the item name: ");
		List<Item> foundItem = tracker.findByName(name);
		if (foundItem.size() != 0) {
			StringBuilder builder = new StringBuilder();
			builder.append(String.format("The list of found Items: %n%-5s%-10s%-16s%-12s", "№", "Name", "Description", "Id"));
			int index = 1;
			for (Item item : foundItem) {
				builder.append(String.format("%n%d%-4s %-10s %-16s %-12s", index++, ".", item.getName(), item.getDescription(), item.getId()));
			}
			input.ask(builder.toString());
		} else {
			input.ask(String.format("There is no items with name \"%s\"%n", name));
		}
	}
}

class DelComments extends BaseAction {
	public DelComments(String name, int key) {
		super(name, key);
	}

	@Override
	public void execute(Input input, Tracker tracker) {
		Item foundItem = getItem(input, tracker);
		if (foundItem != null) {
			List<String> comments = foundItem.getComments();
			if (comments.size() != 0) {
				int[] commentsRange = new int[comments.size()];
				for (int i = 0; i < comments.size(); i++) {
					commentsRange[i] = i + 1;
				}
				int toDeleteCommentNum = input.ask(String.format("%s%n%s", foundItem.showItemComments(), "Enter comment number to delete: "), commentsRange);
				foundItem.delComment(toDeleteCommentNum);
				input.ask("Comment deleted");
			}

		} else {
			input.ask("There is no item with the Id");
		}
	}
}
