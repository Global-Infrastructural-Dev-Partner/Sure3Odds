package com.gidp.sure3odds.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 */
@Entity
@Table(name = "sure_sets")
public class Sets {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sure_sets_seq")
	@SequenceGenerator(name = "sure_sets_seq", sequenceName = "sure_sets_seq", initialValue = 1, allocationSize = 1)

	private Long id;

	private String name;

	private String value;

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
	 *
	 */
	public Sets() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param value
	 */
	public Sets(String name, String value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
