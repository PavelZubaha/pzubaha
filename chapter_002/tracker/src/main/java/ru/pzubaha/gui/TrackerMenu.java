package ru.pzubaha.gui;

import ru.pzubaha.start.Input;
import ru.pzubaha.start.Tracker;

/**
 * Chapter 2. OOP.
 * Lesson 4. Polymorphism.
 *
 * Class Menu managing tracker through command line.
 * Class contains solution of task 396.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 21.05.017
 * @version 2
*/
public class TrackerMenu extends MenuBar {
	/**
	 * Constructor for Menu.
	 * @param input - reference to input instance.
	 * @param tracker - referece to tracker instance.
	 */
	public TrackerMenu(Input input, Tracker tracker) {
		super("Main Menu", input, tracker);
		this.setHeader(new HeaderBar());
		this.setInheritors(new MenuBar[6]);
		this.getInheritors()[0] = new AddMenu("1. Add new Item", input, this, tracker, this.getHeader());
		this.getInheritors()[1] = new ShowAllMenu("2. View all items", input, this, tracker, this.getHeader());
		this.getInheritors()[2] = new FindByIdMenu("3. Find the Item by Id", input, this, tracker, this.getHeader());
		this.getInheritors()[3] = new EditMenu("4. Edit Item", input, this, tracker, this.getHeader());
		this.getInheritors()[4] = new DelMenu("5. Delete Item", input, this, tracker, this.getHeader());
		this.getInheritors()[5] = new FindByNameMenu("6. Find the Item by name", input, this, tracker, this.getHeader());

	}
	/**
	* Special method for tracker menu.
	*/
	public void specialMethod() {
		if ("1".equals(this.getUserTape())) {
			this.getInheritors()[0].run();
		} else if ("2".equals(this.getUserTape())) {
			this.getInheritors()[1].run();
		} else if ("3".equals(this.getUserTape())) {
			this.getInheritors()[2].run();
		} else if ("4".equals(this.getUserTape())) {
			this.getInheritors()[3].run();
		} else if ("5".equals(this.getUserTape())) {
			this.getInheritors()[4].run();
		} else if ("6".equals(this.getUserTape())) {
			this.getInheritors()[5].run();
		}
	}
}

