package com.gidp.sure3odds.entity.games;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "sure_selections")
public class Selections {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sure_selections_seq")
	@SequenceGenerator(name = "sure_selections_seq", sequenceName = "sure_selections_seq", initialValue = 1, allocationSize = 1)

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

	/**
	 *
	 */
	public Selections() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Selections(String name) {
		this.name = name;
	}
}
