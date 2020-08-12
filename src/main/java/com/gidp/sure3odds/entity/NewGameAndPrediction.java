package com.gidp.sure3odds.entity;

public class NewGameAndPrediction {


    private Leagues leagueID;

    private Teams homeTeamID;

    private Teams awayTeamID;

    private Sets setID;

    private String prediction;

    private double odds;

    private int confidenceLevel;

    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
