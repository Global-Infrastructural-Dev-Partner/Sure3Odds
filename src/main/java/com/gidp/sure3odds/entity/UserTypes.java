package com.gidp.sure3odds.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "sure_user_types")

public class UserTypes {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	

	private String name;

	
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

	

	@Override
	public String toString() {
		return "UserTypes [id=" + id + ", name=" + name + "]";
	}

	/**
	 * 
	 */
	public UserTypes() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 */
	public UserTypes(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * @param name
	 */
	public UserTypes(String name) {
		super();
		this.name = name;
	}
	
	
}
