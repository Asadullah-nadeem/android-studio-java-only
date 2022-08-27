package com.livescorescrickets.livescores.Pojo.MultiMatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MultimatchPojo implements Serializable {
    @SerializedName("ImgeURL")
    @Expose
    private String imgeurl;
    @SerializedName("isfinished")
    @Expose
    private int isfinished;
    @SerializedName("ispriority")
    @Expose
    private int ispriority;
    @SerializedName("jsondata")
    @Expose
    private String jsondata;
    @SerializedName("jsonruns")
    @Expose
    private String jsonruns;
    @SerializedName("MatchDate")
    @Expose
    private String matchdate;
    @SerializedName("MatchId")
    @Expose
    private int matchid;
    @SerializedName("Matchtime")
    @Expose
    private String matchtime;
    @SerializedName("MatchType")
    @Expose
    private String matchtype;
    @SerializedName("Result")
    @Expose
    private String result;
    @SerializedName("TeamA")
    @Expose
    private String teama;
    @SerializedName("TeamAImage")
    @Expose
    private String teamaimage;
    @SerializedName("TeamB")
    @Expose
    private String teamb;
    @SerializedName("TeamBImage")
    @Expose
    private String teambimage;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("venue")
    @Expose
    private String venue;

    public String toString() {
        return "MultimatchPojo{imgeurl='" + this.imgeurl + '\'' + ", isfinished=" + this.isfinished + ", ispriority=" + this.ispriority + ", jsondata='" + this.jsondata.toString() + '\'' + ", jsonruns='" + this.jsonruns + '\'' + ", matchdate='" + this.matchdate + '\'' + ", matchid=" + this.matchid + ", matchtime='" + this.matchtime + '\'' + ", matchtype='" + this.matchtype + '\'' + ", result='" + this.result + '\'' + ", teama='" + this.teama + '\'' + ", teamaimage='" + this.teamaimage + '\'' + ", teamb='" + this.teamb + '\'' + ", teambimage='" + this.teambimage + '\'' + ", title='" + this.title + '\'' + ", venue='" + this.venue + '\'' + '}';
    }

    public int getMatchid() {
        return this.matchid;
    }

    public void setMatchid(int i) {
        this.matchid = i;
    }

    public String getMatchdate() {
        return this.matchdate;
    }

    public void setMatchdate(String str) {
        this.matchdate = str;
    }

    public String getMatchtype() {
        return this.matchtype;
    }

    public void setMatchtype(String str) {
        this.matchtype = str;
    }

    public String getImgeurl() {
        return this.imgeurl;
    }

    public void setImgeurl(String str) {
        this.imgeurl = str;
    }

    public String getTeambimage() {
        return this.teambimage;
    }

    public void setTeambimage(String str) {
        this.teambimage = str;
    }

    public String getTeamb() {
        return this.teamb;
    }

    public void setTeamb(String str) {
        this.teamb = str;
    }

    public String getTeamaimage() {
        return this.teamaimage;
    }

    public void setTeamaimage(String str) {
        this.teamaimage = str;
    }

    public String getTeama() {
        return this.teama;
    }

    public void setTeama(String str) {
        this.teama = str;
    }

    public int getIspriority() {
        return this.ispriority;
    }

    public void setIspriority(int i) {
        this.ispriority = i;
    }

    public int getIsfinished() {
        return this.isfinished;
    }

    public void setIsfinished(int i) {
        this.isfinished = i;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String str) {
        this.result = str;
    }

    public String getVenue() {
        return this.venue;
    }

    public void setVenue(String str) {
        this.venue = str;
    }

    public String getMatchtime() {
        return this.matchtime;
    }

    public void setMatchtime(String str) {
        this.matchtime = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getJsondata() {
        return this.jsondata;
    }

    public void setJsondata(String str) {
        this.jsondata = str;
    }

    public String getJsonruns() {
        return this.jsonruns;
    }

    public void setJsonruns(String str) {
        this.jsonruns = str;
    }
}
