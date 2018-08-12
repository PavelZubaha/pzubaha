package pzubaha.classes.inner.start;

import pzubaha.classes.inner.models.User;
import pzubaha.classes.inner.templates.BaseAction;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Chapter 2. OOP.
 * Lesson 7. Exceptions.
 *
 * Main class of tracker app.
 * Solution of task 790.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 17.05.17
 * @version 2
 */
public class StartUI {
    /**
     * input field.
     */
    private Input input;
    /**
     * exit from menu loop.
     */
    private boolean isExit;
    /**
     * Constructor.
     * @param input - initialization input in constructor.
     */
    public StartUI(Input input) {
        this.input = input;
    }
    /**
     * init - init needed objects(instances).
     */
    public void init() {
        Tracker tracker = new Tracker();
        runTracker(tracker);
    }

    private void runTracker(Tracker tracker) {
        final TrackerMenu trackerMenu = new TrackerMenu(this.input, tracker);
        UserAction loginAction = new BaseAction("Login", 0) {
            @Override
            public void execute(Input input, Tracker tracker) {
                String login;
                String p;
                User user = null;
                login = input.ask("Login: ");
                p = input.ask("Password: ");
                user = tracker.getUserByLoginAndPass(login, p);
                if (user != null) {
                    System.out.println("Success!");
                    tracker.setCurUser(user);
                    trackerMenu.delAction(this);
                    trackerMenu.fillActions();
                } else {
                    System.out.println("Login or password not correct");
                p = null;
                }
            }
        };
        trackerMenu.addAction(loginAction);
        /**
         * Inner anonymous class for user action(Exit program).
         */
        BaseAction exitAction = new BaseAction("Exit", 9) {
            /**
             * Method for execute some action.
             */
            public void execute(Input input, Tracker tracker) {
                tracker.exit();
                isExit = true;
            }
        };

        trackerMenu.addAction(exitAction);
        do {
            trackerMenu.show();
            trackerMenu.select(input.ask("Select: ", trackerMenu.getActionRange()));
        } while (!isExit);
    }

    private void init(String propertyPath) {
        Properties props = new Properties();
        try (InputStream in = getClass().getResourceAsStream(propertyPath)) {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (TrackerDB tracker = new TrackerDB(props)) {
            tracker.checkDB();
            runTracker(tracker);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * main.
     * @param args - args.
     */
    public static void main(String[] args) {
        Input input = new ValidateInput();
        new StartUI(input).init("/db/db.properties");
    }
}