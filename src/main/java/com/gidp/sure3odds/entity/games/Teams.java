package com.gidp.sure3odds.entity.games;

import javax.persistence.*;

@Entity
@Table(name = "sure_teams")
public class Teams {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sure_teams_seq")
	@SequenceGenerator(name = "sure_teams_seq", sequenceName = "sure_teams_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	private String name;

	@ManyToOne
	private Leagues league;

	@ManyToOne
	private Countries country;


	private String imageurl;

	public Teams() {
	}

	public Teams(String name, String imageurl) {
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

	public Leagues getLeague() {
		return league;
	}

	public void setLeague(Leagues league) {
		this.league = league;
	}

	public Countries getCountry() {
		return country;
	}

	public void setCountry(Countries country) {
		this.country = country;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
}
