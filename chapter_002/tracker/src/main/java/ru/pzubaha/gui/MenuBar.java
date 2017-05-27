package ru.pzubaha.gui;


import ru.pzubaha.start.Input;
import ru.pzubaha.start.Tracker;

/**
 * Chapter 2. OOP.
 * Lesson 4. Polymorphism.
 *
 * Class MenuBar is the part of GUI Menu.
 * Class contains solution of task 396.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 25.05.017
 * @version 2
*/
public class MenuBar {
	/**
	 * Constractor.
     * @param menuName - name of menu.
	 * @param input - referece to input instance.
     * @param tracker - referece to tracker instance.
	 */
	public MenuBar(String menuName, Input input, Tracker tracker) {
		this(menuName, input, null, tracker);
	}
	/**
	 * Constractor.
	 * @param menuName - name of menu.
	 * @param input - referece to input instance.
	 * @param parent - referece to parent menu.
     * @param tracker - referece to tracker instance.
	*/
	public MenuBar(String menuName, Input input, MenuBar parent, Tracker tracker) {
		this.menuName = menuName;
		this.input = input;
		this.parent = parent;
		this.tracker = tracker;
	}

	/**
	* referece to the parent menu instance.
	*/
	private MenuBar parent;
	/**
	* list of contained menues.
	*/
    private MenuBar[] inheritors;
	/**
     * referece to the input instance.
     */
    private Input input;
	/**
	* name of the menu.
	*/
	private String menuName;
	/**
	* container for the string inputed by user.
	*/
	private String userTape;
	/**
	* header of the menu.
	*/
    private HeaderBar headerBar;
    /**
     * referece to the tracker instance.
     */
    private Tracker tracker;

	/**
	* for making menu bar.
	* @return - StringBuilder instance for the menu.
	*/
	public StringBuilder show() {
		String separator = System.getProperty("line.separator");
		StringBuilder builder = new StringBuilder(150);
		for (int i = 0; i < 4; i++) {
			builder.append(String.format("%n%n%n%n%n%n%n"));
		}
		builder.append(this.getHeader().getHeaderBar());
		builder.append(this.getNameMenu());
		builder.append(separator);
		if (this.getInheritors() != null) {
			for (int i = 0; i < this.getInheritors().length; i++) {
				if (this.inheritors[i] != null) {
					builder.append(String.format("%s%n", this.inheritors[i].getNameMenu()));
				}
			}
		}
		if (this.parent != null) {
			builder.append(String.format("0. To return to %s%n", this.parent.getNameMenu()));
			builder.append(separator);
		} else {
			builder.append("0. Exit program");
			builder.append(separator);
		}
		builder.append("Select an item: ");
		return builder;
	}

	/**
	* Run menu method.
	*/
	public void run() {
		boolean isRunningMenu = true;

		do {
			this.setUserTape(this.getInput().ask(this.show().toString()));
			if ("0".equals(userTape)) {
				break;
			} else {
				this.specialMethod();

			}

		} while (isRunningMenu);
	}

	/**
	* getting name of the menu.
	* @return - name of the menu.
	*/
	public String getNameMenu() {
		return this.menuName;
	}
	/**
	* Special method in each menu class.
	*/
	public void specialMethod() {
	}
	/**
	 * getting string inputed by user.
	 * @return - user inputed string.
	 */
	public String getUserTape() {
		return this.userTape;
	}
    /**
     * setting string inputed by user.
     * @param userTape - string inputed by user.
     */
	public void setUserTape(String userTape) {
		this.userTape = userTape;
	}
	/**
     * for setting inheritors.
     * @param inheritors - list of menu.
     */
	public void setInheritors(MenuBar[] inheritors) {
	    this.inheritors = inheritors;
    }
    /**
     * for setting inheritors.
     * @return - inheritors as list of menu.
     */
    public MenuBar[] getInheritors() {
	    return this.inheritors;
    }
    /**
     * getting header bar.
     * @return - header bar.
     */
    public HeaderBar getHeader() {
        return this.headerBar;
    }
    /**
     * setting header bar.
     * @param headerBar - string inputed by user.
     */
    public void setHeader(HeaderBar headerBar) {
        this.headerBar = headerBar;
    }

    /**
     * getting input interface.
     * @return - input interface.
     */
    public Input getInput() {
        return this.input;
    }
    /**
     * setting input.
     * @param input - input interface.
     */
    public void setInput(Input input) {
        this.input = input;
    }
    /**
     * getting reference to the tracker instance.
     * @return - reference to the tracker instance.
     */
    public Tracker getTracker() {
        return this.tracker;
    }
    /**
     * setting input.
     * @param tracker - tracker instance.
     */
    public void setTracker(Tracker tracker) {
        this.tracker = tracker;
    }

}