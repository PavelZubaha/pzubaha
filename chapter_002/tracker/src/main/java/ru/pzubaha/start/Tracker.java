package ru.pzubaha.start;

import ru.pzubaha.models.*;
import java.util.Random;

public class Tracker {
	private Item[] items = new Item[10];
	private int position = 0;
	private static final Random RN = new Random();
	
	public void add(Item item) {
		item.setId(this.generateId());
		this.items[position++] = item;
	}
	
	protected Item findById(String id) {
		Item result = null;
		for (Item item : items) {
			if (item != null && item.getId().equals(id)) {
				result = item;
				break;
			}
		}
		return result;
	}
	private String generateId() {
		return String.valueOf(System.currentTimeMillis() + RN.nextInt(100));
	}
	public Item[] getAll() {
		Item[] result = new Item[this.position];
		for (int index = 0; index != position; index++) {
			result[index] = this.items[index];
		}
		return result;
	}
	public void update(Item item) {
		String id;
		if(item != null) {
			id = item.getId();
			for (int index = 0; index != position; index++) {
				if (id.equals(items[index].getId())) {
					items[index] = item;
				}
			}
		}
	}
	public void delete(Item item) {
		
	}
}