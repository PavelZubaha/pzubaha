package pzubaha.classes.inner.templates;

import org.apache.commons.lang3.math.NumberUtils;
import pzubaha.classes.inner.models.Item;
import pzubaha.classes.inner.start.Input;
import pzubaha.classes.inner.start.Tracker;
import pzubaha.classes.inner.start.UserAction;

/**
 * Abstract class for base action.
 * Solution of task 790. Abstract and anonymous action.
 */
public abstract class BaseAction implements UserAction {

    /**
     * action key.
     */
	private int key;
    /**
     * action name.
     */
	private String name;

    /**
     * Constructor for action.
     * @param name - action name.
     * @param key - action string.
     */
	public BaseAction(String name, int key) {
		this.name = name;
		this.key = key;
	}

    /**
     * abstract method for any action.
     * @param input - reference to input instance.
     * @param tracker - reference to tracker instance.
     */
	public  abstract void execute(Input input, Tracker tracker);

    /**
     * method for getting string for menu item.
     * @return - String instance.
     */
	public String info() {
        return String.format("%d%-4s%s", this.key, ".", this.name);
	}

    /**
     * method give int key.
     * @return - key for action.
     */
    public int key() {
        return key;
    }
    protected Item getItem(Input input, Tracker tracker) {
        return tracker.findById(NumberUtils.toInt(input.ask("Enter correct item Id: "), 0));
    }
}