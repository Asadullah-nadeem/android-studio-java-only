package com.livescorescrickets.livescores.Pojo.JsonDataFiles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainJsonRuns {
    @SerializedName("jsonruns")
    @Expose
    private Jsonruns jsonruns;

    public Jsonruns getJsonruns() {
        return this.jsonruns;
    }

    public void setJsonruns(Jsonruns jsonruns2) {
        this.jsonruns = jsonruns2;
    }
}
