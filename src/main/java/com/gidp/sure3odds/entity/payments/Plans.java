package com.gidp.sure3odds.entity.payments;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gidp.sure3odds.entity.users.Users;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sure_plans")
public class Plans {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sure_plans_seq")
	@SequenceGenerator(name = "sure_plans_seq", sequenceName = "sure_plans_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@ManyToOne
	private Users user;

	@ManyToOne
	private PlanTypes plantype;

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

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public PlanTypes getPlantype() {
		return plantype;
	}

	public void setPlantype(PlanTypes plantype) {
		this.plantype = plantype;
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
