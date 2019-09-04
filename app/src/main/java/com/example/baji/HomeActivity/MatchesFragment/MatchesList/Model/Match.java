package com.example.baji.HomeActivity.MatchesFragment.MatchesList.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Match implements Serializable {

    @SerializedName("timeStamp")
    @Expose
    private String timeStamp;
    @SerializedName("winnerTeam")
    @Expose
    private Object winnerTeam;
    @SerializedName("teamTwo")
    @Expose
    private TeamTwo teamTwo;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("teamOne")
    @Expose
    private TeamOne teamOne;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Object getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(Object winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    public TeamTwo getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(TeamTwo teamTwo) {
        this.teamTwo = teamTwo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TeamOne getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(TeamOne teamOne) {
        this.teamOne = teamOne;
    }

}
