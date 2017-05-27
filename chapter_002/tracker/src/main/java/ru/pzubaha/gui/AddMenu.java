package ru.pzubaha.gui;

import ru.pzubaha.models.Item;
import ru.pzubaha.start.Input;
import ru.pzubaha.start.Tracker;

/**
 * Chapter 2. OOP.
 * Lesson 4. Polymorphism.
 *
 * Class AddMenu managing tracker through command line.
 * Class contains solution of task 396.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 26.05.017
 * @version 2
 */
class AddMenu extends MenuBar {
    /**
     * Constractor.
     * @param menuName - name of menu.
     * @param input - reference to input instance.
     * @param parent - reference to parent menu.
     * @param tracker - reference to tracker instance.
     * @param headerBar - - reference to HeaderBar instance.
     */
    AddMenu(String menuName, Input input, MenuBar parent, Tracker tracker, HeaderBar headerBar) {
        super(menuName, input, parent, tracker);
        this.setHeader(headerBar);
    }
    @Override
    public void specialMethod() {
        if ("1".equals(this.getUserTape())) {
            String name = this.getInput().ask("Enter name of new item: ");
            String desc = this.getInput().ask("Enter description: ");

            this.getTracker().add(new Item(name, desc));
            this.getInput().ask("Item added");
        } else {
            this.setUserTape(this.getInput().ask("Please input correct value: "));
        }
    }
}
