package com.gidp.sure3odds.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private Leagues leagueID;

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
		return "Teams [id=" + id + ", name=" + name + ", leagueID=" + leagueID + "]";
	}

	/**
	 * @return the leagueID
	 */
	public Leagues getLeagueID() {
		return leagueID;
	}

	/**
	 * @param leagueID the leagueID to set
	 */
	public void setLeagueID(Leagues leagueID) {
		this.leagueID = leagueID;
	}

	/**
	 *
	 */
	public Teams() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 */
	public Teams(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * @param name
	 */
	public Teams(String name) {
		super();
		this.name = name;
	}

}
