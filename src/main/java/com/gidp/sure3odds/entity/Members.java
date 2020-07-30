/**
 * 
 */
package com.gidp.sure3odds.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * @author mac
 *
 */
@Entity
@Table(name = "sure_members")
public class Members {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	
	private String firstname;
	
	private String lastname;
	
	@OneToOne
	private Users userID;

	



	@Override
	public String toString() {
		return "Members [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", userID=" + userID + "]";
	}





	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}





	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the userID
	 */
	public Users getUserID() {
		return userID;
	}





	/**
	 * @param userID the userID to set
	 */
	public void setUserID(Users userID) {
		this.userID = userID;
	}





	/**
	 * @param firstname
	 * @param lastname
	 */
	public Members(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}





	/**
	 * @param id
	 * @param firstname
	 * @param lastname
	 */
	public Members(Long id, String firstname, String lastname) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
	}





	/**
	 * 
	 */
	public Members() {
		super();
	}
	
	
}
