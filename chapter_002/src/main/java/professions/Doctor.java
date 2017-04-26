package professions;

/**
 * Chapter 2. OOP.
 * Lesson 3. Inheritance.
 * Representation of doctor in the code.
 * Class contains solution of task 6837.
 * @author Pavel Zubaha
 * @since 1
 * @version 1
*/
public class Doctor extends Profession {
	/**
	 * specialization of doctor.
	*/
	private String specialization;
	/**
	 * certificate of doctor.
	*/
	private String certificate;
	/**
	 * current patient index.
	*/
	private int currentPatientIndex = 0;
	/**
	 * doctor's signature.
	*/
	private final String doctorSignature = "DOCTOR_SIGNATURE";
	/**
	 * constructor of Doctor.
	 * @param name - doctor's name,
	 * @param age - set doctor age,
	 * @param experience - set doctor experience,
	 * @param location - doctor location,
	 * @param specialization - doctor specialization,
	 * @param certificate - doctor's certificate.
	*/
	public Doctor(String name, int age, int experience, String location, String specialization, String certificate) {
		super(name, age, experience, location);
		this.specialization = specialization;
		this.certificate = certificate;
	}
	/**
	 * constructor of Doctor.
	 * @param specialization - doctor specialization;
	 * @param certificate - doctor's certificate.
	*/
	public Doctor(String specialization, String certificate) {
		super();
		this.specialization = specialization;
		this.certificate = certificate;
	}
	/**
	 * Default constructor of Doctor.
	*/
	public Doctor() {
		this("unknown", "unknown");
	}
	/**
	 * Set doctor's certificate.
	 * @param certificate - doctor certificate.
	*/
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	/**
	 * Get doctor's certificate.
	 * @return certificate - doctor certificate.
	*/
	public String getDoctorCertificate() {
		return this.certificate;
	}
	/**
	 * Doctor accept a patient.
	 * @param patient - current patient.
	*/
	public void acceptsPatient(Patient patient) {
			System.out.println("Please come in!");
			currentPatientIndex += 1;
			patient.setPatientsDoctor(this);
	}
	/**
	 * Find diagnose.
	 * @param patient - current patient.
	 * @return diagnosis of patient.
	*/
	public String diagnose(Patient patient) {
		String diagnosis = "desease";
		return diagnosis;
	}
	/**
	 * Write journal.
	 * @param patient - current patient.
	 * @return string for writing into journal.
	*/
	public String wrightJournal(Patient patient) {
		String separator = System.getProperty("line.separator");
		return String.join(separator, Integer.toString(currentPatientIndex), patient.getName(), Integer.toString(patient.getAge()), patient.getDiagnosis(), this.doctorSignature);
	}
	/**
	 * Prescribed treatment for patient.
	 * @param patient - current patient.
	 * @return string recipe.
	*/
	public String recipe(Patient patient) {
		return "recipe";
	}
	/**
	 * Write Certificate of illness for patient.
	 * @param patient - current patient.
	 * @return string for writing sickness certificate.
	*/
	public String writePatientSicknessCertificate(Patient patient) {
		String separator = System.getProperty("line.separator");
		return String.join(separator,
			"Certificate of Sickness",
			"Number: " + Integer.toString(this.currentPatientIndex),
			"patient name: " + patient.getName(),
			"Diagnosis: " + patient.getDiagnosis(),
			"Doctor name: " + this.getName(),
			"Signature: " + this.doctorSignature);
	}
	/**
	 * get currentPatientIndex.
	 * @return string recipe.
	*/
	public int getPatientIndex() {
		 return this.currentPatientIndex;
	}
}