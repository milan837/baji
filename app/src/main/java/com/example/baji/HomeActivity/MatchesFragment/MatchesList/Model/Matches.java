package com.example.baji.HomeActivity.MatchesFragment.MatchesList.Model;

public class Matches {
    public int id;
    public String teamOne;
    public String teamTwo;

    public Matches(int id, String teamOne, String teamTwo) {
        this.id = id;
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTeamOne(String teamOne) {
        this.teamOne = teamOne;
    }

    public void setTeamTwo(String teamTwo) {
        this.teamTwo = teamTwo;
    }

    public int getId() {
        return id;
    }

    public String getTeamOne() {
        return teamOne;
    }

    public String getTeamTwo() {
        return teamTwo;
    }
}
