package com.baji.Baji.Model;

import javax.persistence.*;

@Entity
public class AmountRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User user;
    private int status;
    private String date;

    public AmountRequest(int id, User user, int status, String date) {
        this.id = id;
        this.user = user;
        this.status = status;
        this.date = date;
    }

    public AmountRequest() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
