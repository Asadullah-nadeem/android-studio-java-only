package com.livescorescrickets.livescores.Pojo.JsonDataFiles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Jsonruns {
    @SerializedName("fav")
    @Expose
    private String fav;
    @SerializedName("rateA")
    @Expose
    private String rateA;
    @SerializedName("rateB")
    @Expose
    private String rateB;
    @SerializedName("runxa")
    @Expose
    private String runxa;
    @SerializedName("runxb")
    @Expose
    private String runxb;
    @SerializedName("sessionA")
    @Expose
    private String sessionA;
    @SerializedName("sessionB")
    @Expose
    private String sessionB;
    @SerializedName("sessionOver")
    @Expose
    private String sessionOver;
    @SerializedName("summary")
    @Expose
    private String summary;

    public String getRunxa() {
        return this.runxa;
    }

    public void setRunxa(String str) {
        this.runxa = str;
    }

    public String getRunxb() {
        return this.runxb;
    }

    public void setRunxb(String str) {
        this.runxb = str;
    }

    public String getFav() {
        return this.fav;
    }

    public void setFav(String str) {
        this.fav = str;
    }

    public String getRateA() {
        return this.rateA;
    }

    public void setRateA(String str) {
        this.rateA = str;
    }

    public String getRateB() {
        return this.rateB;
    }

    public void setRateB(String str) {
        this.rateB = str;
    }

    public String getSessionA() {
        return this.sessionA;
    }

    public void setSessionA(String str) {
        this.sessionA = str;
    }

    public String getSessionB() {
        return this.sessionB;
    }

    public void setSessionB(String str) {
        this.sessionB = str;
    }

    public String getSessionOver() {
        return this.sessionOver;
    }

    public void setSessionOver(String str) {
        this.sessionOver = str;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String str) {
        this.summary = str;
    }
}
