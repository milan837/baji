package com.example.baji.HomeActivity.ProfileFragment.OpenBaji.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpenBid {

    @SerializedName("timeStamp")
    @Expose
    private String timeStamp;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("team")
    @Expose
    private Team team;
    @SerializedName("user")
    @Expose
    private User user;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

