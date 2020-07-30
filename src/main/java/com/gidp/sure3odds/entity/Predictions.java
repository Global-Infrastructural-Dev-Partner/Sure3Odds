package com.gidp.sure3odds.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name = "sure_predictions")
public class Predictions {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Leagues leagueID;
	
	@ManyToOne
	private Teams homeTeamID;
	
	@ManyToOne
	private Teams awayTeamID;
	
	@ManyToOne
	private Sets setID;
	
	@Temporal (TemporalType.DATE)
	private Date matchDate;
	
	@Temporal (TemporalType.TIME)
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

	@Override
	public String toString() {
		return "Predictions [id=" + id + ", leagueID=" + leagueID + ", homeTeamID=" + homeTeamID + ", awayTeamID="
				+ awayTeamID + ", setID=" + setID + ", matchDate=" + matchDate + ", matchTime=" + matchTime
				+ ", prediction=" + prediction + ", odds=" + odds + ", status=" + status + ", confidenceLevel="
				+ confidenceLevel + "]";
	}

	/**
	 * @param matchDate
	 * @param matchTime
	 * @param prediction
	 * @param odds
	 * @param status
	 * @param confidenceLevel
	 */
	public Predictions(Date matchDate, Date matchTime, String prediction, double odds, String status,
			int confidenceLevel) {
		super();
		this.matchDate = matchDate;
		this.matchTime = matchTime;
		this.prediction = prediction;
		this.odds = odds;
		this.status = status;
		this.confidenceLevel = confidenceLevel;
	}

	/**
	 * @param id
	 * @param matchDate
	 * @param matchTime
	 * @param prediction
	 * @param odds
	 * @param status
	 * @param confidenceLevel
	 */
	public Predictions(Long id, Date matchDate, Date matchTime, String prediction, double odds, String status,
			int confidenceLevel) {
		super();
		this.id = id;
		this.matchDate = matchDate;
		this.matchTime = matchTime;
		this.prediction = prediction;
		this.odds = odds;
		this.status = status;
		this.confidenceLevel = confidenceLevel;
	}



	
	
	
	
	
}
