package com.livescorescrickets.livescores.Pojo.JsonDataFiles;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MatchOddsModel {
    @SerializedName(NotificationCompat.CATEGORY_MESSAGE)
    @Expose
    private String msg;
    @SerializedName("Matchst")
    @Expose
    private List<Matchst> playerslist = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<Matchst> getPlayerslist() {
        return this.playerslist;
    }

    public void setPlayerslist(List<Matchst> list) {
        this.playerslist = list;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean bool) {
        this.success = bool;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public static class Matchst {
        @SerializedName("Battingteam")
        @Expose
        private String Battingteam;
        @SerializedName("MrateA")
        @Expose
        private String MrateA;
        @SerializedName("MrateB")
        @Expose
        private String MrateB;
        @SerializedName("Score")
        @Expose
        private String Score;
        @SerializedName("SessionA")
        @Expose
        private String SessionA;
        @SerializedName("SessionB")
        @Expose
        private String SessionB;
        @SerializedName("id")
        @Expose
        private Integer f963id;
        @SerializedName("favourite")
        @Expose
        private String favourite;
        @SerializedName("isfirstinning")
        @Expose
        private String isfirstinning;
        @SerializedName("overs")
        @Expose
        private String overs;
        @SerializedName("subdate")
        @Expose
        private String subdate;
        @SerializedName("wickets")
        @Expose
        private String wickets;

        public String getSubdate() {
            return this.subdate;
        }

        public void setSubdate(String str) {
            this.subdate = str;
        }

        public String getScore() {
            return this.Score;
        }

        public void setScore(String str) {
            this.Score = str;
        }

        public String getSessionA() {
            return this.SessionA;
        }

        public void setSessionA(String str) {
            this.SessionA = str;
        }

        public String getSessionB() {
            return this.SessionB;
        }

        public void setSessionB(String str) {
            this.SessionB = str;
        }

        public String getOvers() {
            return this.overs;
        }

        public void setOvers(String str) {
            this.overs = str;
        }

        public String getBattingteam() {
            return this.Battingteam;
        }

        public void setBattingteam(String str) {
            this.Battingteam = str;
        }

        public String getWickets() {
            return this.wickets;
        }

        public void setWickets(String str) {
            this.wickets = str;
        }

        public String getMrateA() {
            return this.MrateA;
        }

        public void setMrateA(String str) {
            this.MrateA = str;
        }

        public String getMrateB() {
            return this.MrateB;
        }

        public void setMrateB(String str) {
            this.MrateB = str;
        }

        public String getFavourite() {
            return this.favourite;
        }

        public void setFavourite(String str) {
            this.favourite = str;
        }

        public String getIsfirstinning() {
            return this.isfirstinning;
        }

        public void setIsfirstinning(String str) {
            this.isfirstinning = str;
        }

        public Integer getId() {
            return this.f963id;
        }

        public void setId(Integer num) {
            this.f963id = num;
        }
    }
}
