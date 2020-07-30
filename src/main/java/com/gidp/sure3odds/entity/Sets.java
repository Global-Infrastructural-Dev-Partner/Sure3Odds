package com.gidp.sure3odds.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name = "sure_sets")
public class Sets {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;

	@Override
	public String toString() {
		return "Sets [id=" + id + ", name=" + name + "]";
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

	/**
	 * @param id
	 * @param name
	 */
	public Sets(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * @param name
	 */
	public Sets(String name) {
		super();
		this.name = name;
	}

	/**
	 * 
	 */
	public Sets() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
