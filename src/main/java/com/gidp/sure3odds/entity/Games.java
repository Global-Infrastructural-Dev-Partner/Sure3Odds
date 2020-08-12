package com.gidp.sure3odds.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sure_games")
public class Games {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sure_games_seq")
	@SequenceGenerator(name = "sure_games_seq", sequenceName = "sure_games_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name ="leagueid")
	private Leagues leagueID;

	@ManyToOne
	@JoinColumn(name ="hometeamid")
	private Teams homeTeamID;

	@ManyToOne
	@JoinColumn(name ="awayteamid")
	private Teams awayTeamID;

	@ManyToOne
	@JoinColumn(name ="setid")
	private Sets setID;

	private int homeTeamScore;

	private int awayTeamScore;

	private String prediction;

	private double odds;

	private int confidenceLevel;

	private String status;

	@Temporal(TemporalType.DATE)
	private Date matchDate;

	@Temporal(TemporalType.TIME)
	private Date matchTime;

	/**
	 *
	 */
	public Games() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return the homeTeamID
	 */
	public Teams getHomeTeamID() {
		return homeTeamID;
	}

	/**
	 * @param homeTeamID the homeTeamID to set
	 */
	public void setHomeTeamID(Teams homeTeamID) {
		this.homeTeamID = homeTeamID;
	}

	/**
	 * @return the awayTeamID
	 */
	public Teams getAwayTeamID() {
		return awayTeamID;
	}

	/**
	 * @param awayTeamID the awayTeamID to set
	 */
	public void setAwayTeamID(Teams awayTeamID) {
		this.awayTeamID = awayTeamID;
	}

	/**
	 * @return the setID
	 */
	public Sets getSetID() {
		return setID;
	}

	/**
	 * @param setID the setID to set
	 */
	public void setSetID(Sets setID) {
		this.setID = setID;
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
	 * @return the prediction
	 */
	public String getPrediction() {
		return prediction;
	}

	/**
	 * @param prediction the prediction to set
	 */
	public void setPrediction(String prediction) {
		this.prediction = prediction;
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

	@Override
	public String toString() {
		return "Games [id=" + id + ", leagueID=" + leagueID + ", homeTeamID=" + homeTeamID + ", awayTeamID="
				+ awayTeamID + ", setID=" + setID + ", homeTeamScore=" + homeTeamScore + ", awayTeamScore="
				+ awayTeamScore + ", prediction=" + prediction + ", odds=" + odds + ", confidenceLevel="
				+ confidenceLevel + ", status=" + status + ", matchDate=" + matchDate + ", matchTime=" + matchTime
				+ "]";
	}

	/**
	 * @param homeTeamScore
	 * @param awayTeamScore
	 * @param prediction
	 * @param odds
	 * @param confidenceLevel
	 * @param status
	 * @param matchDate
	 * @param matchTime
	 */
	public Games(int homeTeamScore, int awayTeamScore, String prediction, double odds, int confidenceLevel,
			String status, Date matchDate, Date matchTime) {
		super();
		this.homeTeamScore = homeTeamScore;
		this.awayTeamScore = awayTeamScore;
		this.prediction = prediction;
		this.odds = odds;
		this.confidenceLevel = confidenceLevel;
		this.status = status;
		this.matchDate = matchDate;
		this.matchTime = matchTime;
	}

	/**
	 * @param id
	 * @param homeTeamScore
	 * @param awayTeamScore
	 * @param prediction
	 * @param odds
	 * @param confidenceLevel
	 * @param status
	 * @param matchDate
	 * @param matchTime
	 */
	public Games(Long id, int homeTeamScore, int awayTeamScore, String prediction, double odds, int confidenceLevel,
			String status, Date matchDate, Date matchTime) {
		super();
		this.id = id;
		this.homeTeamScore = homeTeamScore;
		this.awayTeamScore = awayTeamScore;
		this.prediction = prediction;
		this.odds = odds;
		this.confidenceLevel = confidenceLevel;
		this.status = status;
		this.matchDate = matchDate;
		this.matchTime = matchTime;
	}

}
