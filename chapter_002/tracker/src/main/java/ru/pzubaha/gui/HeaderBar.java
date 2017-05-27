package ru.pzubaha.gui;

/**
 * Chapter 2. OOP.
 * Lesson 4. Polymorphism.
 *
 * Class HeaderBar header is part of GUI Menu.
 * Class contains solution of task 396.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 25.05.017
 * @version 2
*/
public class HeaderBar {
	/**
	* Header for menu.
	*/
	private StringBuilder headerMenu = new StringBuilder();
	/**
	* copied id varieble.
	*/
	private String copiedId = null;

	/**
	* Constractor.
	*/
	public HeaderBar() {
		this.headerMenu.append(String.format("%n==================%n   T R A C K E R%n==================%n"));
	}
	/**
	* getting StringBuilder instance of header menu.
	* @return header of menu.
	*/
	public StringBuilder getHeaderBar() {
		StringBuilder result = new StringBuilder();
		result.append(this.headerMenu);
		if (this.copiedId != null) {
			result.append(String.format("Copied ID: %s%n", copiedId));
		}
		return result;
	}
	/**
	* for setting copiedId.
	* @param copiedId - copied by user id.
	*/
	protected void setCopiedId(String copiedId) {
		this.copiedId = copiedId;
	}
	/**
	 * for getting copied Id.
	 * @return -  copied by user id.
	 */
	protected String getCopiedId() {
		String result = copiedId.toString();
		return result;
	}
}