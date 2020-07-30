package com.gidp.sure3odds.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "sure_plan_types")
public class PlanTypes {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;

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
	
	
}
