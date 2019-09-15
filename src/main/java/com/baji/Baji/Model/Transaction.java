package com.baji.Baji.Model;

import javax.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String orderId;
    @ManyToOne
    public User user;
    public String timeStamp;
    public int amount;

    public Transaction() {
    }

    public Transaction(String orderId, User user, String timeStamp, int amount) {
        this.orderId = orderId;
        this.user = user;
        this.timeStamp = timeStamp;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public User getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
