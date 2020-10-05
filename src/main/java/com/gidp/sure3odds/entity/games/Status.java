package com.gidp.sure3odds.entity.games;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "sure_status")
public class Status {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sure_status_seq")
	@SequenceGenerator(name = "sure_status_seq", sequenceName = "sure_status_seq", initialValue = 1, allocationSize = 1)

	private Long id;

	private String name;

	private String type;


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
	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Status(String name, String type) {
		this.name = name;
		this.type = type;
	}
}
