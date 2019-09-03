package com.example.baji.HomeActivity.MatchesFragment.MatchesList.Model;

import java.util.List;

public class GameWIthMatchResponsePojo {

    private String gameTitle;
    private int totalMatches;
    private int gameId;

    private List<Matches> Matches;

    public GameWIthMatchResponsePojo(String gameTitle, int totalMatches, int gameId, List<Matches> matches) {
        this.gameTitle = gameTitle;
        this.totalMatches = totalMatches;
        this.gameId = gameId;
        Matches = matches;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public void setMatches(List<Matches> matches) {
        Matches = matches;
    }

    public void setTotalMatches(int totalMatches) {
        this.totalMatches = totalMatches;
    }

    public int getGameId() {
        return gameId;
    }

    public int getTotalMatches() {
        return totalMatches;
    }

    public List<Matches> getMatches() {
        return Matches;
    }

    public String getGameTitle() {
        return gameTitle;
    }
}
