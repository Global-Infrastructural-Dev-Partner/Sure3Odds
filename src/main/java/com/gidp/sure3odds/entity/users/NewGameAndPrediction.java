package com.gidp.sure3odds.entity.users;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gidp.sure3odds.entity.games.Sets;
import com.gidp.sure3odds.entity.games.Teams;
import com.gidp.sure3odds.entity.games.Countries;
import com.gidp.sure3odds.entity.games.Leagues;

import java.util.Date;

public class NewGameAndPrediction {

    private Countries countryID;

    private Leagues leagueID;

    private Teams homeTeamID;

    private Teams awayTeamID;

    private Sets setID;

    private String prediction;

    private double odds;

    private int confidenceLevel;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Africa/Lagos")
    private Date matchDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,  pattern="HH:mm:ss", timezone = "Africa/Lagos")
    private Date matchTime;

    public Leagues getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(Leagues leagueID) {
        this.leagueID = leagueID;
    }

    public Teams getHomeTeamID() {
        return homeTeamID;
    }

    public void setHomeTeamID(Teams homeTeamID) {
        this.homeTeamID = homeTeamID;
    }

    public Teams getAwayTeamID() {
        return awayTeamID;
    }

    public void setAwayTeamID(Teams awayTeamID) {
        this.awayTeamID = awayTeamID;
    }

    public Sets getSetID() {
        return setID;
    }

    public void setSetID(Sets setID) {
        this.setID = setID;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public double getOdds() {
        return odds;
    }

    public void setOdds(double odds) {
        this.odds = odds;
    }

    public int getConfidenceLevel() {
        return confidenceLevel;
    }

    public void setConfidenceLevel(int confidenceLevel) {
        this.confidenceLevel = confidenceLevel;
    }

    public Countries getCountryID() {
        return countryID;
    }

    public void setCountryID(Countries countryID) {
        this.countryID = countryID;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public Date getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(Date matchTime) {
        this.matchTime = matchTime;
    }
}
