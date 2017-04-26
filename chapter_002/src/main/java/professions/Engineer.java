package professions;

/**
 * Chapter 2. OOP.
 * Lesson 3. Inheritance.
 * Representation of engineer in the code.
 * Class contains solution of task 6837.
 * @author Pavel Zubaha
 * @since 1
 * @version 1
*/
public class Engineer extends Profession {
	/**
	 * Specialization of engineer.
	*/
	private String specialization;
	/**
	 * Fields contains tasks of engineer.
	 * Note! The Engineer can process only 10 tasks simultaneously.
	*/
	private String[] taskList = new String[10];
	/**
	 * Field contains solutions and statuses of tasks, with the same index.
	*/
	private String[] solutionList = new String[10];
	/**
	 * Amount of tasks of engineer.
	*/
	private int taskAmount = 0;
	/**
	 * Constructor for Engineer.
	 * @param name -  name of engineer.
	 * @param specialization - specialization of engineer.
	 * @param age - age of engineer.
	 * @param experience - experience of engineer.
	 * @param location - current location of engineer.
	*/
	public Engineer(String name, String specialization, int experience, int age, String location) {
		super(name, age, experience, location);
		this.specialization = specialization;
	}
	/**
	 * Constructor for Engineer.
	 * @param name -  name of engineer.
	 * @param specialization - specialization of engineer.
	*/
	public Engineer(String name, String specialization) {
		this(name, specialization, 3, 30, "Earth");
	}
	/**
	 * Constructor for Engineer.
	 * @param name -  name of engineer.
	*/
	public Engineer(String name) {
		this(name, "unknown specialization");
	}
	/**
	 * Default constructor for Engineer.
	*/
	public Engineer() {
		this("unnamed");
	}
	/**
	 * Method for receive task to engineer.
	 * @param task - task to engineer.
	 * @return boolean value of received task or not received.
	*/
	public boolean receiveTask(String task) {
		String separator = System.getProperty("line.separator");
		if (taskAmount + 1 < 10) {
			this.taskList[taskAmount] = task;
			StringBuilder builder = new StringBuilder();
			builder.append(separator).append("Received").append(separator);
			this.solutionList[taskAmount] = builder.toString();
			taskAmount++;
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Method for develop task.
	 * @param taskIndex - index of task to develop.
	*/
	public void develop(int taskIndex) {
		String separator = System.getProperty("line.separator");
		if (taskIndex > -1 && taskIndex < 10 && taskList[taskIndex] != null) {
			StringBuilder builder = new StringBuilder();
			builder.append(separator).append("Procesing").append(separator);
			builder.append("Solution").append(separator); // There is should be engineer's logic, instead "solution".
			builder.append("Finished").append(separator);
			this.solutionList[taskIndex] = builder.toString();
		}
	}
	/**
	 * Method for develop task.
	 * @param taskIndex - index of task that status will be returned.
	 * @return status of the taskIndex task.
	*/
	public String taskStatus(int taskIndex) {
		if (taskIndex >= 0 && taskIndex < 10 && solutionList[taskIndex] != null) {
			if (solutionList[taskIndex].contains("Finished")) {
				return "Finished";
			} else if (solutionList[taskIndex].contains("Procesing")) {
				return "Processing";
			} else if (solutionList[taskIndex].contains("Received")) {
				return "Received";
			}
		}
		return "Index is not correct";
	}
}