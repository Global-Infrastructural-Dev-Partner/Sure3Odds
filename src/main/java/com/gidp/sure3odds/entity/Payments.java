package com.gidp.sure3odds.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name = "sure_payments")
public class Payments {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Users userID;
	
	@ManyToOne
	private PlanTypes planTypeID;
	
	@Temporal (TemporalType.DATE)
	private Date date;
	
	private String type;
	
	private String platform;

	/**
	 * 
	 */
	public Payments() {
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
	 * @return the planTypeID
	 */
	public PlanTypes getPlanTypeID() {
		return planTypeID;
	}

	/**
	 * @param planTypeID the planTypeID to set
	 */
	public void setPlanTypeID(PlanTypes planTypeID) {
		this.planTypeID = planTypeID;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * @param platform the platform to set
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	@Override
	public String toString() {
		return "Payments [id=" + id + ", userID=" + userID + ", planTypeID=" + planTypeID + ", date=" + date + ", type="
				+ type + ", platform=" + platform + "]";
	}

	/**
	 * @param date
	 * @param type
	 * @param platform
	 */
	public Payments(Date date, String type, String platform) {
		super();
		this.date = date;
		this.type = type;
		this.platform = platform;
	}

	/**
	 * @param id
	 * @param date
	 * @param type
	 * @param platform
	 */
	public Payments(Long id, Date date, String type, String platform) {
		super();
		this.id = id;
		this.date = date;
		this.type = type;
		this.platform = platform;
	}
	
	
	
}
