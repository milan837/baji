package com.example.baji.HomeActivity.ActiveBajiListFragment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OnboardBaji {

    @SerializedName("timeStamp")
    @Expose
    private String timeStamp;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("teamTwo")
    @Expose
    private TeamTwo teamTwo;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("teamOne")
    @Expose
    private TeamOne teamOne;

    @SerializedName("gameTitle")
    @Expose
    private String gameTitle;

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
