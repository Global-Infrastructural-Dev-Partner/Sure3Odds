package com.gidp.sure3odds.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sure_users")

public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private String ID;

	@ManyToOne
	private UserTypes userTypeID;

	private String email;

	private String phone;

	private String password;

	@Temporal(TemporalType.DATE)
	private String datejoined;

	private String status;

	private String device_token;

	private String assigned;

	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
	}

	/**
	 * @return the userTypeID
	 */
	public UserTypes getUserTypeID() {
		return userTypeID;
	}

	/**
	 * @param userTypeID the userTypeID to set
	 */
	public void setUserTypeID(UserTypes userTypeID) {
		this.userTypeID = userTypeID;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the datejoined
	 */
	public String getDatejoined() {
		return datejoined;
	}

	/**
	 * @param datejoined the datejoined to set
	 */
	public void setDatejoined(String datejoined) {
		this.datejoined = datejoined;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the device_token
	 */
	public String getDevice_token() {
		return device_token;
	}

	/**
	 * @param device_token the device_token to set
	 */
	public void setDevice_token(String device_token) {
		this.device_token = device_token;
	}

	/**
	 * @return the assigned
	 */
	public String getAssigned() {
		return assigned;
	}

	/**
	 * @param assigned the assigned to set
	 */
	public void setAssigned(String assigned) {
		this.assigned = assigned;
	}

	@Override
	public String toString() {
		return "Users [ID=" + ID + ", userTypeID=" + userTypeID + ", email=" + email + ", phone=" + phone
				+ ", password=" + password + ", datejoined=" + datejoined + ", status=" + status + ", device_token="
				+ device_token + ", assigned=" + assigned + "]";
	}

	/**
	 * @param email
	 * @param phone
	 * @param password
	 * @param datejoined
	 * @param status
	 * @param device_token
	 * @param assigned
	 */
	public Users(String email, String phone, String password, String datejoined, String status, String device_token,
			String assigned) {
		super();
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.datejoined = datejoined;
		this.status = status;
		this.device_token = device_token;
		this.assigned = assigned;
	}

	/**
	 * @param iD
	 * @param email
	 * @param phone
	 * @param password
	 * @param datejoined
	 * @param status
	 * @param device_token
	 * @param assigned
	 */
	public Users(String iD, String email, String phone, String password, String datejoined, String status,
			String device_token, String assigned) {
		super();
		ID = iD;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.datejoined = datejoined;
		this.status = status;
		this.device_token = device_token;
		this.assigned = assigned;
	}

	

	
	
	

}
