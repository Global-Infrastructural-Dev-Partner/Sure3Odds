package com.gidp.sure3odds.entity.payments;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sure_plan_types")
public class PlanTypes {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sure_plan_types_seq")
	@SequenceGenerator(name = "sure_plan_types_seq", sequenceName = "sure_plan_types_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	private String name;
	
	private BigDecimal  amount;

	/**
	 *
	 */
	public PlanTypes() {
		super();
	}

	@Override
	public String toString() {
		return "PlanTypes [id=" + id + ", name=" + name + "]";
	}

	/**
	 * @param id
	 * @param name
	 */
	public PlanTypes(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * @param name
	 */
	public PlanTypes(String name) {
		super();
		this.name = name;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @param id
	 * @param name
	 * @param amount
	 */
	public PlanTypes(Long id, String name, BigDecimal amount) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
	}

	/**
	 * @param name
	 * @param amount
	 */
	public PlanTypes(String name, BigDecimal amount) {
		super();
		this.name = name;
		this.amount = amount;
	}


	
	

}
