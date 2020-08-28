package com.gidp.sure3odds.entity.games;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sure_games")
public class Games {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sure_games_seq")
	@SequenceGenerator(name = "sure_games_seq", sequenceName = "sure_games_seq", initialValue = 1, allocationSize = 1)
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
	private Sets set;

	private int homeTeamScore;

	private int awayTeamScore;

	@ManyToOne
	private Selections selection;

	private double odds;

	private int confidenceLevel;

	private String status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Africa/Lagos")
	private Date matchDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING,  pattern="HH:mm:ss", timezone = "Africa/Lagos")
	private Date matchTime;

	/**
	 *
	 */
	public Games() {
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
	 * @return the homeTeamScore
	 */
	public int getHomeTeamScore() {
		return homeTeamScore;
	}

	/**
	 * @param homeTeamScore the homeTeamScore to set
	 */
	public void setHomeTeamScore(int homeTeamScore) {
		this.homeTeamScore = homeTeamScore;
	}

	/**
	 * @return the awayTeamScore
	 */
	public int getAwayTeamScore() {
		return awayTeamScore;
	}

	/**
	 * @param awayTeamScore the awayTeamScore to set
	 */
	public void setAwayTeamScore(int awayTeamScore) {
		this.awayTeamScore = awayTeamScore;
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
	 * @return the matchDate
	 */
	public Date getMatchDate() {
		return matchDate;
	}

	/**
	 * @param matchDate the matchDate to set
	 */
	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}

	/**
	 * @return the matchTime
	 */
	public Date getMatchTime() {
		return matchTime;
	}

	/**
	 * @param matchTime the matchTime to set
	 */
	public void setMatchTime(Date matchTime) {
		this.matchTime = matchTime;
	}

	public Games(int homeTeamScore, int awayTeamScore, double odds, int confidenceLevel, String status, Date matchDate, Date matchTime) {
		this.homeTeamScore = homeTeamScore;
		this.awayTeamScore = awayTeamScore;
		this.odds = odds;
		this.confidenceLevel = confidenceLevel;
		this.status = status;
		this.matchDate = matchDate;
		this.matchTime = matchTime;
	}


	public Games(Long id, int homeTeamScore, int awayTeamScore, double odds, int confidenceLevel, String status, Date matchDate, Date matchTime) {
		this.id = id;
		this.homeTeamScore = homeTeamScore;
		this.awayTeamScore = awayTeamScore;
		this.odds = odds;
		this.confidenceLevel = confidenceLevel;
		this.status = status;
		this.matchDate = matchDate;
		this.matchTime = matchTime;
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

	public Sets getSet() {
		return set;
	}

	public void setSet(Sets set) {
		this.set = set;
	}

	public Selections getSelection() {
		return selection;
	}

	public void setSelection(Selections selection) {
		this.selection = selection;
	}
}
