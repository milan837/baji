package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.OpenBajiListFragment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OpenBajiResponsePojo {

    @SerializedName("openBids")
    @Expose
    private List<OpenBid> openBids = null;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    public List<OpenBid> getOpenBids() {
        return openBids;
    }

    public void setOpenBids(List<OpenBid> openBids) {
        this.openBids = openBids;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
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