package com.gidp.sure3odds.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sure_leagues")
public class Leagues {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sure_leagues_seq")
	@SequenceGenerator(name = "sure_leagues_seq", sequenceName = "sure_leagues_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	private String name;

	/**
	 *
	 */
	public Leagues() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Leagues [id=" + id + ", name=" + name + "]";
	}

	/**
	 * @param id
	 * @param name
	 */
	public Leagues(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * @param name
	 */
	public Leagues(String name) {
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
