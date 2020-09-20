package com.gidp.sure3odds.entity.games;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gidp.sure3odds.entity.users.Users;

import javax.persistence.*;
import java.time.LocalDate;
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
	private LocalDate matchdate;

	@JsonFormat(shape = JsonFormat.Shape.STRING,  pattern="HH:mm", timezone = "Africa/Lagos")
	private Date matchtime;

	@ManyToOne
	private Selections selections;

	private double odds;

	@ManyToOne
	private Status status;

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

	public LocalDate getMatchdate() {
		return matchdate;
	}

	public void setMatchdate(LocalDate matchdate) {
		this.matchdate = matchdate;
	}

	public Date getMatchtime() {
		return matchtime;
	}

	public void setMatchtime(Date matchtime) {
		this.matchtime = matchtime;
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




	public Predictions(Long id, LocalDate matchdate, Date matchtime, double odds) {
		this.id = id;
		this.matchdate = matchdate;
		this.matchtime = matchtime;
		this.odds = odds;
	}



	public Predictions(LocalDate matchdate, Date matchtime, double odds) {
		this.matchdate = matchdate;
		this.matchtime = matchtime;
		this.odds = odds;

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

	public Selections getSelections() {
		return selections;
	}

	public void setSelections(Selections selections) {
		this.selections = selections;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
