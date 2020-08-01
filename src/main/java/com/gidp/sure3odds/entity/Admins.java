/**
 *
 */
package com.gidp.sure3odds.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author mac
 *
 */
@Entity
@Table(name = "sure_admins")
public class Admins {

	@Id
	@GeneratedValue(generator = "admin-generator")
	@GenericGenerator(name = "admin-generator", parameters = @Parameter(name = "prefix", value = "admin"), strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	private String firstname;

	private String lastname;

	@OneToOne
	private Users userID;

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

	@Override
	public String toString() {
		return "Admins [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", userID=" + userID + "]";
	}

	/**
	 *
	 */
	public Admins() {
		super();
	}

	/**
	 * @param id
	 * @param firstname
	 * @param lastname
	 */
	public Admins(Long id, String firstname, String lastname) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	/**
	 * @param firstname
	 * @param lastname
	 */
	public Admins(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}

}
