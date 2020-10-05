package com.gidp.sure3odds.entity.games;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;

public class NewGameAndPrediction {

    private Countries country;

    private Leagues league;

    private Teams hometeam;

    private Teams awayteam;

    private Sets sets;

    private Selections selections;

    private double odds;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Africa/Lagos")
    private LocalDate matchdate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,  pattern="HH:mm", timezone = "Africa/Lagos")
    private Date matchtime;

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

    public double getOdds() {
        return odds;
    }

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
}
