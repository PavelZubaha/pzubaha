package professions;

/**
 * Chapter 2. OOP.
 * Lesson 3. Inheritance.
 * Representation of patient in the code.
 * Class contains solution of task 6837.
 * @author Pavel Zubaha
 * @since 1
 * @version 1
*/
public class Patient extends Person {
	/**
	 * symptoms of patient.
	*/
	private String[] symptoms;
	/**
	 * current diagnosis of patient.
	*/
	private String diagnosis;
	/**
	 * current patient's doctor.
	*/
	private Doctor patientsDoctor;
	/**
	 * current patient's doctor.
	*/
	private String sicknessCertificate;
	/**
	 * Default constructor of patient.
	*/
	public Patient() {
		this("unnamed");
	}
	/**
	 * Constructor of patient.
	 * @param name - name of patient.
	*/
	public Patient(String name) {
		this(name, 0);
	}
	/**
	 * Constructor of patient.
	 * @param name - patient name.
	 * @param age - age of patient.
	*/
	public Patient(String name, int age) {
		super(name, age);
		this.diagnosis = "unknown";
		this.patientsDoctor = new Doctor();
	}
	/**
	 * Method for seting patient symptoms.
	 * @param symptoms - symptoms of patient.
	*/
	public void setSymptoms(String[] symptoms) {
		this.symptoms = symptoms;
	}
	/**
	 * Method get patient's symptoms.
	 * @return patient symptoms.
	*/
	public String[] getSymptoms() {
		return this.symptoms;
	}
	/**
	 * Method for set patient's doctor.
	 * @param patientsDoctor - doctor that accepts patient.
	*/
	public void setPatientsDoctor(Doctor patientsDoctor) {
		this.patientsDoctor = patientsDoctor;
	}
	/**
	 * Method for get patient's doctor.
	 * @return doctor of patient.
	*/
	public Doctor getPatientsDoctor() {
		return this.patientsDoctor;
	}
	/**
	 * Method for set patient's sickness certificate.
	*/
	public void setSicknessCertificate() {
		this.sicknessCertificate = this.patientsDoctor.writePatientSicknessCertificate(this);
	}
	/**
	 * Method for get patient's sickness certificate.
	 * @return - sickness certificate of patient.
	*/
	public String getSicknessCertificate() {
		return this.sicknessCertificate;
	}
	/**
	 * Method for set patient's diagnosis.
	*/
	public void setDiagnosis() {
		this.diagnosis = this.patientsDoctor.diagnose(this);
	}
	/**
	 * Method for get patient's diagnosis.
	 * @return diagnosis of patient.
	*/
	public String getDiagnosis() {
		return this.diagnosis;
	}
}