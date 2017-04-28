package professions;

/**
 * Chapter 2. OOP.
 * Lesson 3. Inheritance.
 * Representation of engineer in the code.
 * Class contains solution of task 6837.
 * @author Pavel Zubaha
 * @since 27.04.2017
 * @version 1
*/
public class Teacher extends Profession {
	/**
	 * Subject is taught by the teacher.
	*/
	private String subject;
	/**
	 * Constructor for teacher.
	 * @param name - name of the teacher.
	 * @param subject - subject of the teacher.
	 * @param age - age of the teacher.
	 * @param experience - experience of the teacher.
	 * @param location - current location.
	*/
	public Teacher(String name, String subject, int age, int experience, String location) {
		super(name, age, experience, location);
		this.subject = subject;
	}
	/**
	 * Constructor for teacher.
	 * @param subject - subject of the teacher.
	*/
	public Teacher(String subject) {
		super();
		this.subject = subject;
	}
	/**
	 * Constructor for teacher.
	 * @param name - name of the teacher.
	 * @param subject - subject of the teacher.
	*/
	public Teacher(String name, String subject) {
		this(name, subject, 30, 0, "Earth");
	}
	/**
	 * Default constructor for teacher.
	*/
	public Teacher() {
		this("default subject");
	}
	/**
	 * Lecture for group.
	 * @param group - group of students listening lecture.
	 * @return lecture.
	*/
	public Lecture lecture(Group group) {
		return new Lecture();
	}
}
/**
 * Class Lecture.
*/
class Lecture {
}
/**
 * Class Student.
*/
class Student extends Person {
	/**
	 * Student memory.
	*/
	private String memory = "";
	/**
	 * add lecture or object to memory.
	 * @param lecture - lecture.
	*/
	void addToMemory(Lecture lecture) {
		this.memory = this.memory.concat(lecture.toString());
	}
}
/**
 * Class Group for students group.
*/
class Group {
	/**
	 * Students group identifier.
	*/
	private String identifier;
	/**
	 * Students list of group. Max capacity is 34 students.
	*/
	private Student[] studentsList = new Student[40];
	/**
	 * Constructor for group.
	 * @param identifier - identifier of group.
	*/
	Group(String identifier) {
		this.identifier = identifier;
	}
	/**
	 * For add student into group.
	 * @param student - student.
	*/
	void addStudent(Student student) {
		for (int i = 0; i < 40; i++) {
			if (this.studentsList[i] == null) {
				this.studentsList[i] = student;
				break;
			}
		}
	}
	/**
	 * For remove student from group.
	 * @param student - student.
	*/
	void removeStudent(Student student) {
		for (int i = 0; i < 40; i++) {
			if (student.equals(this.studentsList[i])) {
				this.studentsList[i] = null;
				break;
			}
		}
	}
	/**
	 * For remove student from group.
	 * @param teacher - teacher.
	*/
	void lectureToStudents(Teacher teacher) {
		for (int i = 0; i < 40; i++) {
			if (this.studentsList[i] != null) {
				this.studentsList[i].addToMemory(teacher.lecture(this));
			}
		}
	}
}