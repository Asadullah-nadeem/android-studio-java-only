package com.livescorescrickets.livescores.Pojo.JsonDataFiles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainJsonData {
    @SerializedName("jsondata")
    @Expose
    private Jsondata jsondata;

    public Jsondata getJsondata() {
        return this.jsondata;
    }

    public void setJsondata(Jsondata jsondata2) {
        this.jsondata = jsondata2;
    }
}
