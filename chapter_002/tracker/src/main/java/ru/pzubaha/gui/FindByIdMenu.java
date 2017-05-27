package ru.pzubaha.gui;

import ru.pzubaha.models.Item;
import ru.pzubaha.start.Input;
import ru.pzubaha.start.Tracker;

/**
 * Chapter 2. OOP.
 * Lesson 4. Polymorphism.
 *
 * Class FindByIdMenu managing tracker through command line.
 * Class contains solution of task 396.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 26.05.017
 * @version 2
*/
class FindByIdMenu extends MenuBar {
	/**
	 * Constractor.
	 * @param menuName - name of menu.
	 * @param input - reference to input instance.
	 * @param parent - reference to parent menu.
	 * @param tracker - reference to tracker instance.
	 * @param headerBar - reference to HeaderBar instance.
	 */
	FindByIdMenu(String menuName, Input input, MenuBar parent, Tracker tracker, HeaderBar headerBar) {
		super(menuName, input, parent, tracker);
		this.setHeader(headerBar);
	}
	@Override
	public void specialMethod() {
		Item foundItem;
		String iD = null;
		String userAnswer = this.getInput().ask("Enter item Id, or tape 'p' to paste iD: ");
		if ("p".equals(userAnswer) || "P".equals(userAnswer)) {
			if (this.getHeader().getCopiedId() != null) {
				iD = this.getHeader().getCopiedId();
			}
		} else {
			iD = userAnswer;
		}
		foundItem = this.getTracker().findById(iD);
		if (foundItem != null) {
			this.getInput().ask(String.format("%nName of item is: %s%nDescription item is: %s", foundItem.getName(), foundItem.getDescription()));
		} else {
			this.getInput().ask("There is't any items with the Id");
		}
	}
}
