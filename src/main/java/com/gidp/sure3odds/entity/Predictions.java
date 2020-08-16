package com.gidp.sure3odds.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "sure_predictions")
public class Predictions {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sure_predictions_seq")
	@SequenceGenerator(name = "sure_predictions_seq", sequenceName = "sure_predictions_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "countryid")
	private Countries countryID;

	@ManyToOne
	@JoinColumn(name = "leagueid")
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

	@Temporal(TemporalType.DATE)
	private Date matchDate;

	@Temporal(TemporalType.TIME)
	private Date matchTime;

	private String prediction;

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

	public Predictions(Long id, Date matchDate, Date matchTime, String prediction, double odds, String status, int confidenceLevel) {
		this.id = id;
		this.matchDate = matchDate;
		this.matchTime = matchTime;
		this.prediction = prediction;
		this.odds = odds;
		this.status = status;
		this.confidenceLevel = confidenceLevel;
	}

	public Predictions(Date matchDate, Date matchTime, String prediction, double odds, String status, int confidenceLevel) {
		this.matchDate = matchDate;
		this.matchTime = matchTime;
		this.prediction = prediction;
		this.odds = odds;
		this.status = status;
		this.confidenceLevel = confidenceLevel;
	}

	public Countries getCountryID() {
		return countryID;
	}

	public void setCountryID(Countries countryID) {
		this.countryID = countryID;
	}

	@Override
	public String toString() {
		return "Predictions{" +
				"id=" + id +
				", countryID=" + countryID +
				", leagueID=" + leagueID +
				", homeTeamID=" + homeTeamID +
				", awayTeamID=" + awayTeamID +
				", setID=" + setID +
				", matchDate=" + matchDate +
				", matchTime=" + matchTime +
				", prediction='" + prediction + '\'' +
				", odds=" + odds +
				", status='" + status + '\'' +
				", confidenceLevel=" + confidenceLevel +
				'}';
	}
}
