package pzubaha.classes.inner.start;

/**
 * Chapter 2. OOP.
 * Lesson 4. Polymorphism.
 * <p>
 * Implementation basic actions of user
 * Solution for task 787.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 2
 * @since 25.05.017
 */
public interface UserAction {

    /**
     * special key for each action.
     * @return - special key.
     */
    int key();

    /**
     * perfom somthing.
     * @param input - reference to input instance.
     * @param tracker - reference to tracker instance.
     */
    void execute(Input input, Tracker tracker);

    /**
     * information about action.
     * @return - information.
     */
    String info();
}