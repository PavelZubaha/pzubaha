package pzubaha.classes.inner.start;


import pzubaha.classes.inner.models.Item;
import pzubaha.classes.inner.models.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	 * Array contains different user selections.
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
		        new AddItem(),
                new TrackerMenu.ShowAll(),
                new EditItem(),
                new AddComments(),
                new ShowComments(),
                new DelItem(),
                new TrackerMenu.FindItemById(),
                new FindItemByName())
        );
	}
    /**
     * Get action range.
     * @return - array of integer values of action.
     */
	public int[] getActionRange() {
	    int[] result = new int[this.actions.size()];
	    for (int index = 0; index < actions.size(); index++) {
            result[index] = index;
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
		this.actions.get(key).execute(this.input, this.tracker);
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
	private class AddItem implements UserAction {
		/**
		 * Special key for each action.
		 * @return user action key.
		 */
		public int key() {
			return 0;
		}

		/**
		 * action for each menu item.
		 * @param input - reference to input instance.
		 * @param tracker - reference to tracker instance.
		 */
		public void execute(Input input, Tracker tracker) {
			String name = input.ask("Please enter the task name: ");
			String desc = input.ask("Please enter the task description: ");
			tracker.add(new Task(name, desc));
			input.ask("Click Enter to continue: ");
		}

		/**
		 * String information about menu item.
		 * @return - info about menu item.
		 */
		public String info() {
			return String.format("%d%-4s%s", this.key(), ".", "Add new item");
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
	private static class ShowAll implements UserAction {

		/**
		 * Special key for each action.
		 * @return user action key.
		 */
		public int key() {
			return 1;
		}

		/**
		 * action for each menu item.
		 * @param input - reference to input instance.
		 * @param tracker - reference to tracker instance.
		 */
		public void execute(Input input, Tracker tracker) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(String.format("The list of found Items: %n%-5s%-10s%-16s%-12s", "№", "Name", "Description", "Id"));
			int index = 1;
			for (Item item : tracker.getAll()) {
				stringBuilder.append(String.format("%n%d%-4s %-10s %-16s %-12s", index++, ".", item.getName(), item.getDescription(), item.getId()));
			}
            input.ask(String.format("%s%n%s", stringBuilder.toString(), "Click Enter to continue: "));
		}
		/**
		 * String information about menu item.
		 * @return - info about menu item.
		 */
		public String info() {
			return String.format("%d%-4s%s", this.key(), ".", "Show all items");
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
	private static class FindItemById implements UserAction {

		/**
		 * Special key for each action.
		 * @return user action key.
		 */
		public int key() {
			return 6;
		}

		/**
		 * action for each menu item.
		 * @param input - reference to input instance.
		 * @param tracker - reference to tracker instance.
		 */
		public void execute(Input input, Tracker tracker) {
			String iD = input.ask("Enter item Id: ");
			Item foundItem = tracker.findById(iD);
			if (foundItem != null) {
				input.ask(String.format("Name: %s%nDescription: %s%nItem Id: %s%n Comments: %n%s", foundItem.getName(), foundItem.getDescription(), foundItem.getId(), foundItem.showItemComments()));
			} else {
				input.ask("There is no item with the Id");
			}
		}
		/**
		 * String information about menu item.
		 * @return - info about menu item.
		 */
		public String info() {
			return String.format("%d%-4s%s", this.key(), ".", "Find item by the Id");
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
class EditItem implements UserAction {

	/**
	 * Special key for each action.
	 * @return user action key.
	 */
	public int key() {
		return 2;
	}

	/**
	 * action for each menu item.
	 * @param input - reference to input instance.
	 * @param tracker - reference to tracker instance.
	 */
	public void execute(Input input, Tracker tracker) {
		String iD = input.ask("Enter item Id: ");
		Item foundItem = tracker.findById(iD);
		if (foundItem != null) {
			String name = input.ask("Enter new name of item: ");
			String desc = input.ask("Enter new description: ");
			Item newItem = new Item(name, desc);
			newItem.setId(iD);
			tracker.editItem(newItem);
			input.ask("Item edited");
		} else {
			input.ask("There is no item with the Id");
		}
	}
	/**
	 * String information about menu item.
	 * @return - info about menu item.
	 */
	public String info() {
		return String.format("%d%-4s%s", this.key(), ".", "Edit Item");
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
class AddComments implements UserAction {

	/**
	 * Special key for each action.
	 * @return user action key.
	 */
	public int key() {
		return 3;
	}

	/**
	 * action for each menu item.
	 * @param input - reference to input instance.
	 * @param tracker - reference to tracker instance.
	 */
	public void execute(Input input, Tracker tracker) {
		String iD = input.ask("Enter item Id: ");
		Item foundItem = tracker.findById(iD);
		if (foundItem != null) {
			String comment = input.ask("Enter comment to add: ");
			foundItem.addComment(comment);
			input.ask("Comment added");
		} else {
			input.ask("There is no item with the Id");
		}
	}
	/**
	 * String information about menu item.
	 * @return - info about menu item.
	 */
	public String info() {
		return String.format("%d%-4s%s", this.key(), ".", "Add comment to Item");
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
class ShowComments implements UserAction {

	/**
	 * Special key for each action.
	 * @return user action key.
	 */
	public int key() {
		return 4;
	}

	/**
	 * action for each menu item.
	 * @param input - reference to input instance.
	 * @param tracker - reference to tracker instance.
	 */
	public void execute(Input input, Tracker tracker) {
		String iD = input.ask("Enter item Id: ");
		Item foundItem = tracker.findById(iD);
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
	/**
	 * String information about menu item.
	 * @return - info about menu item.
	 */
	public String info() {
		return String.format("%d%-4s%s", this.key(), ".", "Show comments");
	}
}
/**
 * Chapter 2. OOP.
 * Lesson 7. Exceptions.
 *
 * Class managing tracker through command line.
 * Class contains solution of task 787.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 25.06.17
 * @version 2
 */
class DelItem implements UserAction {

	/**
	 * Special key for each action.
	 * @return user action key.
	 */
	public int key() {
		return 5;
	}

	/**
	 * action for each menu item.
	 * @param input - reference to input instance.
	 * @param tracker - reference to tracker instance.
	 */
	public void execute(Input input, Tracker tracker) {
		String iD = input.ask("Enter item Id: ");
		Item foundItem = tracker.findById(iD);
		if (foundItem != null) {
			tracker.delete(foundItem);
			input.ask("Item deleted");
		} else {
			input.ask("There is no item with the Id");
		}
	}
	/**
	 * String information about menu item.
	 * @return - info about menu item.
	 */
	public String info() {
		return String.format("%d%-4s%s", this.key(), ".", "Delete item");
	}
}
/**
 * Chapter 2. OOP.
 * Lesson 7. Exceptions.
 *
 * Class managing tracker through command line.
 * Class contains solution of task 787.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 25.06.17
 * @version 2
 */
class FindItemByName implements UserAction {

	/**
	 * Special key for each action.
	 * @return user action key.
	 */
	public int key() {
		return 7;
	}

	/**
	 * action for each menu item.
	 * @param input - reference to input instance.
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
	/**
	 * String information about menu item.
	 * @return - info about menu item.
	 */
	public String info() {
		return String.format("%d%-4s%s", this.key(), ".", "Find item by the name");
	}
}