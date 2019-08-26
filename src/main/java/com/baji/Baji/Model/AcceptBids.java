package com.baji.Baji.Model;

import javax.persistence.*;

@Entity
public class AcceptBids {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private OpenBids openBids;
    @ManyToOne
    private User user;
    private String timeStamp;
    @ManyToOne
    public Team team;
    @ManyToOne
    private Matches matches;

    public AcceptBids() {
    }

    public AcceptBids(OpenBids openBids, User user, String timeStamp, Team team, Matches matches) {
        this.openBids = openBids;
        this.user = user;
        this.timeStamp = timeStamp;
        this.team = team;
        this.matches = matches;
    }

    public void setMatches(Matches matches) {
        this.matches = matches;
    }

    public Matches getMatches() {
        return matches;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OpenBids getOpenBids() {
        return openBids;
    }

    public void setOpenBids(OpenBids openBids) {
        this.openBids = openBids;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
