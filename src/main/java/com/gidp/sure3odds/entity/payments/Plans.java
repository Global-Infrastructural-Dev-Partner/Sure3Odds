package com.gidp.sure3odds.entity.payments;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gidp.sure3odds.entity.users.Users;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sure_plans")
public class Plans {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sure_plans_seq")
	@SequenceGenerator(name = "sure_plans_seq", sequenceName = "sure_plans_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name ="userid")
	private Users userID;

	@ManyToOne
	@JoinColumn(name ="plantypeid")
	private PlanTypes planTypeID;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Africa/Lagos")
	private Date startDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Africa/Lagos")
	private Date endDate;

	/**
	 *
	 */
	public Plans() {
		super();
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
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Plans [id=" + id + ", userID=" + userID + ", planTypeID=" + planTypeID + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}

	/**
	 * @param startDate
	 * @param endDate
	 */
	public Plans(Date startDate, Date endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/**
	 * @param id
	 * @param startDate
	 * @param endDate
	 */
	public Plans(Long id, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
	}

}
