package ru.pzubaha.gui;

import ru.pzubaha.models.Item;
import ru.pzubaha.start.Input;
import ru.pzubaha.start.Tracker;

/**
 * Chapter 2. OOP.
 * Lesson 4. Polymorphism.
 *
 * Class EditMenu managing tracker through command line.
 * Class contains solution of task 396.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 26.05.017
 * @version 2
 */
class EditMenu extends MenuBar {
    /**
     * Constractor.
     * @param menuName - name of menu.
     * @param input - reference to input instance.
     * @param parent - reference to parent menu.
     * @param tracker - reference to tracker instance.
     * @param header - reference to HeaderBar instance.
     */
    EditMenu(String menuName, Input input, MenuBar parent, Tracker tracker, HeaderBar header) {
        super(menuName, input, parent, tracker);
        this.setHeader(header);
    }
    @Override
    public void specialMethod() {
        Item foundItem;
        String userAnswer = this.getInput().ask("Enter item Id, or tape 'p' to paste iD: ");
        String iD = null;
        if ("p".equals(userAnswer) || "P".equals(userAnswer)) {
            if (this.getHeader().getCopiedId() != null) {
                iD = this.getHeader().getCopiedId();
            }
        } else {
            iD = userAnswer;
        }
        foundItem = this.getTracker().findById(iD);
        if (foundItem != null) {
            String name = this.getInput().ask("Enter new name of item: ");
            String desc = this.getInput().ask("Enter new description: ");
            Item newItem = new Item(name, desc);
            newItem.setId(iD);
            this.getTracker().update(newItem);
            this.getInput().ask("Item edited");
        } else {
            this.getInput().ask("There is no item with the Id");
        }
    }
}