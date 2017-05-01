package ru.pzubaha.start;
import ru.pzubaha.models.*;
public class StartUI {
	public static void main(String[] args) {
		Tracker tracker = new Tracker();
		tracker.add(new Task("New task", "New description"));
		tracker.add(new Task("Second task", "second description"));
		for (Item item : tracker.getAll()) {
			System.out.println(item.getName());
		}
	}
}
