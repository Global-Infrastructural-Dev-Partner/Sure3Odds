package com.gidp.sure3odds.entity;

public class NewUser {

	private String firstname;

	private String lastname;

	private UserTypes usertypeID;

	/**
	 *
	 */
	public NewUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the usertypeID
	 */
	public UserTypes getUsertypeID() {
		return usertypeID;
	}

	/**
	 * @param usertypeID the usertypeID to set
	 */
	public void setUsertypeID(UserTypes usertypeID) {
		this.usertypeID = usertypeID;
	}

}
