package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BajiOnboardListFragment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BajiOnboardResponsePojo  {

    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("onboardBaji")
    @Expose
    private List<OnboardBaji> onboardBaji = null;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<OnboardBaji> getOnboardBaji() {
        return onboardBaji;
    }

    public void setOnboardBaji(List<OnboardBaji> onboardBaji) {
        this.onboardBaji = onboardBaji;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
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