package com.gidp.sure3odds.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sure_users")

public class Users {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sure_users_seq")
	@SequenceGenerator(name = "sure_users_seq", sequenceName = "sure_users_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@ManyToOne
	private UserTypes userTypeID;

	private String email;

	private String phone;

	private String password;

	@Temporal(TemporalType.DATE)
	private Date datejoined;

	private String status;

	private String device_token;

	private String assigned = "Pending";

	/**
	 *
	 */
	public Users() {
		super();
		// TODO Auto-generated constructor stub
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
	public Date getDatejoined() {
		return datejoined;
	}

	/**
	 * @param datejoined the datejoined to set
	 */
	public void setDatejoined(Date datejoined) {
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

	/**
	 * @param email
	 * @param phone
	 * @param password
	 * @param datejoined
	 * @param status
	 * @param device_token
	 * @param assigned
	 */
	public Users(String email, String phone, String password, Date datejoined, String status, String device_token,
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
	 * @param id
	 * @param email
	 * @param phone
	 * @param password
	 * @param datejoined
	 * @param status
	 * @param device_token
	 * @param assigned
	 */
	public Users(Long id, String email, String phone, String password, Date datejoined, String status,
			String device_token, String assigned) {
		super();
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.datejoined = datejoined;
		this.status = status;
		this.device_token = device_token;
		this.assigned = assigned;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", userTypeID=" + userTypeID + ", email=" + email + ", phone=" + phone
				+ ", password=" + password + ", datejoined=" + datejoined + ", status=" + status + ", device_token="
				+ device_token + ", assigned=" + assigned + "]";
	}

}
