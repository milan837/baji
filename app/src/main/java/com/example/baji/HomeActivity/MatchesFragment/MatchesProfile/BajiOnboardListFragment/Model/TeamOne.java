package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BajiOnboardListFragment.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamOne {

    @SerializedName("teamName")
    @Expose
    private String teamName;
    @SerializedName("userImageUrl")
    @Expose
    private String userImageUrl;
    @SerializedName("teamId")
    @Expose
    private Integer teamId;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("teamImageUrl")
    @Expose
    private String teamImageUrl;
    @SerializedName("username")
    @Expose
    private String username;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTeamImageUrl() {
        return teamImageUrl;
    }

    public void setTeamImageUrl(String teamImageUrl) {
        this.teamImageUrl = teamImageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}