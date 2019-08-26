package com.baji.Baji.Model;

import javax.persistence.*;

@Entity
public class Matches {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private GameTitle gameTitle;

    private String timeStamp;
    @ManyToOne
    private Team teamOne;
    @ManyToOne
    private Team teamTwo;
    @ManyToOne
    private Team winnerTeam;

    public Matches() {
    }

    public Matches(GameTitle gameTitle, String timeStamp, Team teamOne, Team teamTwo, Team winnerTeam) {
        this.gameTitle = gameTitle;
        this.timeStamp = timeStamp;
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.winnerTeam = winnerTeam;
    }

    public void setGameTitle(GameTitle gameTitle) {
        this.gameTitle = gameTitle;
    }

    public GameTitle getGameTitle() {
        return gameTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Team getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(Team teamOne) {
        this.teamOne = teamOne;
    }

    public Team getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(Team teamTwo) {
        this.teamTwo = teamTwo;
    }

    public Team getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(Team winnerTeam) {
        this.winnerTeam = winnerTeam;
    }
}
