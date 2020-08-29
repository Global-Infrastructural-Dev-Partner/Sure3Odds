package com.gidp.sure3odds.entity.games;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gidp.sure3odds.entity.users.Users;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "sure_predictions")
public class Predictions {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sure_predictions_seq")
	@SequenceGenerator(name = "sure_predictions_seq", sequenceName = "sure_predictions_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@ManyToOne
	private Countries country;

	@ManyToOne
	private Leagues league;

	@ManyToOne
	private Teams hometeam;

	@ManyToOne
	private Teams awayteam;

	@ManyToOne
	private Users user;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Africa/Lagos")
	private LocalDate matchDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING,  pattern="HH:mm:ss", timezone = "Africa/Lagos")
	private LocalTime matchTime;

	@ManyToOne
	private Selections selection;

	private double odds;

	private String status;

	private int confidenceLevel;

	/**
	 *
	 */
	public Predictions() {
		super();
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
	 * @return the matchDate
	 */
	public LocalDate getMatchDate() {
		return matchDate;
	}

	/**
	 * @param matchDate the matchDate to set
	 */
	public void setMatchDate(LocalDate matchDate) {
		this.matchDate = matchDate;
	}

	/**
	 * @return the matchTime
	 */
	public LocalTime getMatchTime() {
		return matchTime;
	}

	/**
	 * @param matchTime the matchTime to set
	 */
	public void setMatchTime(LocalTime matchTime) {
		this.matchTime = matchTime;
	}


	/**
	 * @return the odds
	 */
	public double getOdds() {
		return odds;
	}

	/**
	 * @param odds the odds to set
	 */
	public void setOdds(double odds) {
		this.odds = odds;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the confidenceLevel
	 */
	public int getConfidenceLevel() {
		return confidenceLevel;
	}

	/**
	 * @param confidenceLevel the confidenceLevel to set
	 */
	public void setConfidenceLevel(int confidenceLevel) {
		this.confidenceLevel = confidenceLevel;
	}

	public Predictions(Long id, LocalDate matchDate, LocalTime matchTime, double odds, String status, int confidenceLevel) {
		this.id = id;
		this.matchDate = matchDate;
		this.matchTime = matchTime;
		this.odds = odds;
		this.status = status;
		this.confidenceLevel = confidenceLevel;
	}



	public Predictions(LocalDate matchDate, LocalTime matchTime, double odds, String status, int confidenceLevel) {
		this.matchDate = matchDate;
		this.matchTime = matchTime;
		this.odds = odds;
		this.status = status;
		this.confidenceLevel = confidenceLevel;
	}

	public Countries getCountry() {
		return country;
	}

	public void setCountry(Countries country) {
		this.country = country;
	}

	public Leagues getLeague() {
		return league;
	}

	public void setLeague(Leagues league) {
		this.league = league;
	}

	public Teams getHometeam() {
		return hometeam;
	}

	public void setHometeam(Teams hometeam) {
		this.hometeam = hometeam;
	}

	public Teams getAwayteam() {
		return awayteam;
	}

	public void setAwayteam(Teams awayteam) {
		this.awayteam = awayteam;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Selections getSelection() {
		return selection;
	}

	public void setSelection(Selections selection) {
		this.selection = selection;
	}
}
