package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaytmChecksumResponsePojo {

    @SerializedName("CHECKSUMHASH")
    @Expose
    private String cHECKSUMHASH;
    @SerializedName("ORDER_ID")
    @Expose
    private Object oRDERID;
    @SerializedName("payt_STATUS")
    @Expose
    private String paytSTATUS;

    public String getCHECKSUMHASH() {
        return cHECKSUMHASH;
    }

    public void setCHECKSUMHASH(String cHECKSUMHASH) {
        this.cHECKSUMHASH = cHECKSUMHASH;
    }

    public Object getORDERID() {
        return oRDERID;
    }

    public void setORDERID(Object oRDERID) {
        this.oRDERID = oRDERID;
    }

    public String getPaytSTATUS() {
        return paytSTATUS;
    }

    public void setPaytSTATUS(String paytSTATUS) {
        this.paytSTATUS = paytSTATUS;
    }

}