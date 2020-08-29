package com.gidp.sure3odds.entity.users;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sure_users")

public class Users {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sure_users_seq")
	@SequenceGenerator(name = "sure_users_seq", sequenceName = "sure_users_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@ManyToOne
	private UserTypes usertype;
	
	@Column(unique = true)
	private String email;
	
	@Column(unique = true)
	private String phone;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	private String firstname;

	private String lastname;


	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Africa/Lagos")
	private LocalDate datejoined;

	private String status;

	@Lob
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

	public UserTypes getUsertype() {
		return usertype;
	}

	public void setUsertype(UserTypes usertype) {
		this.usertype = usertype;
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
	public LocalDate getDatejoined() {
		return datejoined;
	}

	/**
	 * @param datejoined the datejoined to set
	 */
	public void setDatejoined(LocalDate datejoined) {
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Users(Long id, String email, String phone, String password, String firstname, String lastname,
				 LocalDate datejoined, String status, String device_token, String assigned) {
		super();
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.datejoined = datejoined;
		this.status = status;
		this.device_token = device_token;
		this.assigned = assigned;
	}

	public Users(String email, String phone, String password, String firstname, String lastname, LocalDate datejoined,
			String status, String device_token, String assigned) {
		super();
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.datejoined = datejoined;
		this.status = status;
		this.device_token = device_token;
		this.assigned = assigned;
	}



}
