package professions;

/**
 * Chapter 2. OOP.
 * Lesson 3. Inheritance.
 * Representation of person in the code.
 * Class contains solution of task 6837.
 * @author Pavel Zubaha
 * @since 24.04.2017
 * @version 1
*/
public class Person {
	/**
	 * name of person.
	*/
	private String name;
	/**
	 * age of person.
	*/
	private int age;
	/**
	 * Default constructor for person.
	*/
	public Person() {
		this("unnamed");
	}
	/**
	 * Constructor for person.
	 * @param name - name of person.
	*/
	public Person(String name) {
		this(name, 0);
	}
	/**
	 * Constructor for person.
	 * @param name - name of person.
	 * @param age - age of person.
	*/
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	/**
	 * @return name of person.
	*/
	public String getName() {
		return this.name;
	}
	/**
	 * @return age of person.
	*/
	public int getAge() {
		return this.age;
	}
	/**
	 * @param age - age of person.
	*/
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @param name - name of person.
	*/
	public void setAge(String name) {
		this.name = name;
	}
}