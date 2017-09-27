package pzubaha.classes.inner.start;


import pzubaha.classes.inner.models.Item;
import pzubaha.classes.inner.templates.BaseAction;

import java.util.List;

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
		TrackerMenu trackerMenu = new TrackerMenu(this.input, tracker);
        trackerMenu.fillActions();
        /**
         * inner anonymous class for deleting comments.
         */
        UserAction delCommentAction = new UserAction() {
            @Override
            public int key() {
                return 8;
            }

            @Override
            public void execute(Input input, Tracker tracker) {
                String iD = input.ask("Enter item Id: ");
                Item foundItem = tracker.findById(iD);
                if (foundItem != null) {
                    List<String> comments = foundItem.getComments();
                    if (comments.size() != 0) {
                        int[] commentsRange = new int[comments.size()];
                        for (int i = 0; i < comments.size(); i++) {
                            commentsRange[i] = i + 1;
                        }
                        int toDeleteCommentNum = input.ask(String.format("%s%n%s", foundItem.showItemComments() + "Enter comment number to delete: "), commentsRange);
                        foundItem.delComment(toDeleteCommentNum);
                        input.ask("Comment deleted");
                    }

                } else {
                    input.ask("There is no item with the Id");
                }
            }

            @Override
            public String info() {
                return String.format("%d%-4s%s", this.key(), ".", "Delete comment");
            }
        };
        /**
         * Inner anonymous abstract class for user action(Exit program).
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
        trackerMenu.addAction(delCommentAction);
        trackerMenu.addAction(exitAction);
		do {
			trackerMenu.show();
			trackerMenu.select(input.ask("Select: ", trackerMenu.getActionRange()));
		} while (!isExit);
	}
	/**
	 * main.
	 * @param args - args.
	 */
	public static void main(String[] args) {
		Input input = new ValidateInput();
		new StartUI(input).init();
	}
}
