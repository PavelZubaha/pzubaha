package ru.pzubaha.models;
/**
 * Chapter 2. OOP.
 * Lesson 4. Encapsulation.
 *
 * Class Item implements different type of tracker issue/request.
 * Class contains solution of task 396.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 02.05.017
 * @version 2
 */
public class Item {
	/**
	 * Unique field for each example of Item.
	*/
	private String id;
	/**
	 * name of each example.
	 */
	private String name;
	/**
	 * description.
	 */
	private String description;
	/**
	 * name of each example.
	 */
	private long create;
	/**
	 * default constructor for Item.
	 */
	public Item() {
	}
	/**
	 * construtor for Item.
	 * @param name - name.
	 * @param  description - description.
	 */
	public Item(String name, String description) {
		this.name = name;
		this.description = description;
		this.create = System.currentTimeMillis();
	}
	/**
	 * method for getting name of item example.
	 * @return name.
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * method for getting description of item example.
	 * @return description.
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * method for getting create of item example.
	 * @return create.
	 */
	public long getCreate() {
		return this.create;
	}
	/**
	 * method for getting id of item example.
	 * @return id.
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * method for setting id of item example.
	 * @param id - id needed to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
}