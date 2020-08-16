package com.gidp.sure3odds.entity;

import javax.persistence.*;

@Entity
@Table(name = "sure_leagues")
public class Leagues {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sure_leagues_seq")
	@SequenceGenerator(name = "sure_leagues_seq", sequenceName = "sure_leagues_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	private String name;

	private String imageurl;

	@ManyToOne
	@JoinColumn(name ="countryid")
	private Countries countryID;

	/**
	 *
	 */
	public Leagues() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Leagues(String name, String imageurl) {
		this.name = name;
		this.imageurl = imageurl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Countries getCountryID() {
		return countryID;
	}

	public void setCountryID(Countries countryID) {
		this.countryID = countryID;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
}
