package com.baji.Baji.Model;

import javax.persistence.*;

@Entity
public class OpenBids {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Matches matches;
    @ManyToOne
    private Team team;
    @ManyToOne
    private User user;
    private String timeStamp;
    private double amount;
    private int active;

    public OpenBids() {
    }

    public OpenBids(Matches matches, Team team, User user, String timeStamp, double amount,int active) {
        this.matches = matches;
        this.team = team;
        this.user = user;
        this.timeStamp = timeStamp;
        this.amount = amount;
        this.active=active;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Matches getMatches() {
        return matches;
    }

    public void setMatches(Matches matches) {
        this.matches = matches;
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

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
