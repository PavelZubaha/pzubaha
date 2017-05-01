package professions;

/**
 * Chapter 2. OOP.
 * Lesson 3. Inheritance.
 * Representation of professions in the code.
 * Class contains solution of task 6837.
 * @author Pavel Zubaha
 * @since 24.04.2017
 * @version 1
*/
public class Profession extends Person {
	/**
	 * experience of worker.
	*/
	private int experience;
	/**
	 * current location.
	*/
	private String location;
	/**
	 * Constructor for profession.
	 * @param name -  name of worker.
	 * @param age - age of worker.
	 * @param experience - experience of worker.
	 * @param location - current location.
	*/
	public Profession(String name, int age, int experience, String location) {
		super(name, age);
		this.experience = experience;
		this.location = location;
	}
/**
 * default constructor.
*/
	public Profession() {
		super();
		this.experience = 0;
		this.location = "Earth";
	}
	/**
	 * Set worker's experience.
	 * @param experience - experience of doctor.
	*/
	public void setExperience(int experience) {
		this.experience = experience;
	}
	/**
	 * @return worker experience.
	*/
	public int getExperience() {
		return this.experience;
	}
	/**
	 * set location.
	 * @param location - location of worker.
	*/
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return current worker location.
	*/
	public String getLocation() {
		return this.location;
	}
	/**
	 * Method represents profession in string format.
	 * @return a string representation of the profession.
	*/
	public String professionToString() {
		String separator = System.getProperty("line.separator");
		return String.join(separator, this.getName(), Integer.toString(this.getAge()), Integer.toString(this.getExperience()), this.getLocation());
	}
}