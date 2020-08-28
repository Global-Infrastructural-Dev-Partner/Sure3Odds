package com.gidp.sure3odds.entity.payments;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gidp.sure3odds.entity.users.Users;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sure_payments")
public class Payments {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sure_payments_seq")
	@SequenceGenerator(name = "sure_payments_seq", sequenceName = "sure_payments_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@ManyToOne
	private Users user;

	@ManyToOne
	private PlanTypes plantype;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Africa/Lagos")
	private Date paymentdate;

	private String paymenttype;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(Date paymentdate) {
		this.paymentdate = paymentdate;
	}

	public String getPaymenttype() {
		return paymenttype;
	}

	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

	public Payments(Date paymentdate, String paymenttype, String platform, String referenceCode) {
		super();
		this.paymentdate = paymentdate;
		this.paymenttype = paymenttype;
		this.platform = platform;
		this.referenceCode = referenceCode;
	}

	public Payments(Long id, Date paymentdate, String paymenttype, String platform, String referenceCode) {
		super();
		this.id = id;
		this.paymentdate = paymentdate;
		this.paymenttype = paymenttype;
		this.platform = platform;
		this.referenceCode = referenceCode;
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
}
