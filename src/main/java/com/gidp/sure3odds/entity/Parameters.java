package com.gidp.sure3odds.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sure_parameters")
public class Parameters {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sure_parameters_seq")
	@SequenceGenerator(name = "sure_parameters_seq", sequenceName = "sure_parameters_seq", initialValue = 1, allocationSize = 1)
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
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 *
	 */
	public Parameters() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Parameters [id=" + id + ", name=" + name + ", value=" + value + "]";
	}

	/**
	 * @param name
	 * @param value
	 */
	public Parameters(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	/**
	 * @param id
	 * @param name
	 * @param value
	 */
	public Parameters(Long id, String name, String value) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
	}

}
