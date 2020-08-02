package com.gidp.sure3odds.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sure_payments")
public class Payments {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sure_payments_seq")
	@SequenceGenerator(name = "sure_payments_seq", sequenceName = "sure_payments_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@ManyToOne
	private Users userID;

	@ManyToOne
	private PlanTypes planTypeID;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String type;

	private String platform;

	@Lob
	private String referenceCode;

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

	/**
	 * @return the referenceCode
	 */
	public String getReferenceCode() {
		return referenceCode;
	}

	/**
	 * @param referenceCode the referenceCode to set
	 */
	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

	@Override
	public String toString() {
		return "Payments [id=" + id + ", userID=" + userID + ", planTypeID=" + planTypeID + ", date=" + date + ", type="
				+ type + ", platform=" + platform + ", referenceCode=" + referenceCode + "]";
	}

	/**
	 * @param date
	 * @param type
	 * @param platform
	 * @param referenceCode
	 */
	public Payments(Date date, String type, String platform, String referenceCode) {
		super();
		this.date = date;
		this.type = type;
		this.platform = platform;
		this.referenceCode = referenceCode;
	}

	/**
	 * @param id
	 * @param date
	 * @param type
	 * @param platform
	 * @param referenceCode
	 */
	public Payments(Long id, Date date, String type, String platform, String referenceCode) {
		super();
		this.id = id;
		this.date = date;
		this.type = type;
		this.platform = platform;
		this.referenceCode = referenceCode;
	}

}
