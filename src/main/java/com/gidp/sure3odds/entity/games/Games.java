package com.gidp.sure3odds.entity.games;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
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
    private Sets sets;

    private int hometeamscore;

    private int awayteamscore;

    @ManyToOne
    private Selections selections;

    private double odds;
    @ManyToOne
    private Status status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Africa/Lagos")
    private LocalDate matchdate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Africa/Lagos")
    private Date matchtime;

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

    public int getHometeamscore() {
        return hometeamscore;
    }

    public void setHometeamscore(int hometeamscore) {
        this.hometeamscore = hometeamscore;
    }

    public int getAwayteamscore() {
        return awayteamscore;
    }

    public void setAwayteamscore(int awayteamscore) {
        this.awayteamscore = awayteamscore;
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

    public Games(int hometeamscore, int awayteamscore, double odds, LocalDate matchdate, Date matchtime) {
        this.hometeamscore = hometeamscore;
        this.awayteamscore = awayteamscore;
        this.odds = odds;
        this.matchdate = matchdate;
        this.matchtime = matchtime;
    }


    public Games(Long id, int hometeamscore, int awayteamscore, double odds, LocalDate matchdate, Date matchtime) {
        this.id = id;
        this.hometeamscore = hometeamscore;
        this.awayteamscore = awayteamscore;
        this.odds = odds;
        this.matchdate = matchdate;
        this.matchtime = matchtime;
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

    public Sets getSets() {
        return sets;
    }

    public void setSets(Sets sets) {
        this.sets = sets;
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
