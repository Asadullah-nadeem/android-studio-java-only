package com.livescorescrickets.livescores.Pojo.MultiMatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetLiveLinePojo {
    @SerializedName("Appversion")
    @Expose
    private Object appversion;
    @SerializedName("jsondata")
    @Expose
    private String jsondata;
    @SerializedName("jsonruns")
    @Expose
    private String jsonruns;

    public String getJsonruns() {
        return this.jsonruns;
    }

    public void setJsonruns(String str) {
        this.jsonruns = str;
    }

    public String getJsondata() {
        return this.jsondata;
    }

    public void setJsondata(String str) {
        this.jsondata = str;
    }

    public Object getAppversion() {
        return this.appversion;
    }

    public void setAppversion(Object obj) {
        this.appversion = obj;
    }
}
