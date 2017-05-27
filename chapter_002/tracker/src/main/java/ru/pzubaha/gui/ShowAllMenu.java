package ru.pzubaha.gui;

import ru.pzubaha.models.Item;
import ru.pzubaha.start.Input;
import ru.pzubaha.start.Tracker;

/**
 * Chapter 2. OOP.
 * Lesson 4. Polymorphism.
 *
 * Class ShowAllMenu managing tracker through command line.
 * Class contains solution of task 396.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 26.05.017
 * @version 2
 */
class ShowAllMenu extends MenuBar {
    /**
     * Constractor.
     * @param menuName - name of menu.
     * @param input - referece to input instance.
     * @param parent - referece to parent menu.
     * @param tracker - referece to tracker instance.
     * @param headerBar - reference to HeaderBar insctance.
     */
    ShowAllMenu(String menuName, Input input, MenuBar parent, Tracker tracker, HeaderBar headerBar) {
        super(menuName, input, parent, tracker);
        this.setHeader(headerBar);
    }
    @Override
    public void specialMethod() {
        if ("2".equals(this.getUserTape())) {
            String separator = System.getProperty("line.separator");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.format("List of Items: %n"));
            for (Item item : this.getTracker().getAll()) {
                stringBuilder.append(item.getName() + " " + item.getDescription() + separator);
            }
            this.getInput().ask(stringBuilder.toString());
        }

    }
}