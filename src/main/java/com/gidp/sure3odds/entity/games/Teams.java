package com.gidp.sure3odds.entity.games;

import com.gidp.sure3odds.entity.games.Countries;
import com.gidp.sure3odds.entity.games.Leagues;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	@JoinColumn(name ="leagueid")
	private Leagues leagueID;

	@ManyToOne
	@JoinColumn(name ="countryid")
	private Countries countryID;


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

	public Leagues getLeagueID() {
		return leagueID;
	}

	public void setLeagueID(Leagues leagueID) {
		this.leagueID = leagueID;
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
