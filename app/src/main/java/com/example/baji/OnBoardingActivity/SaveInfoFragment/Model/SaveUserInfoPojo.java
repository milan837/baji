package com.example.baji.OnBoardingActivity.SaveInfoFragment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveUserInfoPojo {
    @SerializedName("user_details")
    @Expose
    private UserDetails userDetails;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}