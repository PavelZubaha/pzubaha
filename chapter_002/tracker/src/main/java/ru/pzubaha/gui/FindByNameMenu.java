package ru.pzubaha.gui;

import ru.pzubaha.models.Item;
import ru.pzubaha.start.Input;
import ru.pzubaha.start.Tracker;

/**
 * Chapter 2. OOP.
 * Lesson 4. Polymorphism.
 *
 * Class FindByNameMenu managing tracker through command line.
 * Class contains solution of task 396.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 26.05.017
 * @version 2
 */
class FindByNameMenu extends MenuBar {
	/**
	 * Constractor.
     * @param menuName - name of menu.
     * @param input - reference to input instance.
     * @param parent - reference to parent menu.
     * @param tracker - reference to tracker instance.
     * @param header - reference to HeaderBar instance.
     */
	FindByNameMenu(String menuName, Input input, MenuBar parent, Tracker tracker, HeaderBar header) {
		super(menuName, input, parent, tracker);
		this.setHeader(header);
	}
	@Override
	public void specialMethod() {
		if ("6".equals(getUserTape())) {
			Item[] items;
			items = this.getTracker().findByName(this.getInput().ask("Enter the name of searched items: "));
			if (items.length == 0) {
				this.getInput().ask("There isn't any found items");
			} else {
				StringBuilder builder = new StringBuilder(150);
				builder.append(String.format("The list of found items Id:%n"));
				for (int index = 0; index < items.length; index++) {
					builder.append(String.format("%d.  %s%n", index + 1, items[index].getId()));
				}
				String userAnswer =  this.getInput().ask(String.format("%n%sTo copy Id input the number of the item (1...n): ", builder.toString()));
				for (int i = 0; i < items.length; i++) {
					if (new String(new Integer(i + 1).toString()).equals(userAnswer)) {
						this.getHeader().setCopiedId(items[i].getId());
						break;
					}
				}
			}
		}
	}
}
