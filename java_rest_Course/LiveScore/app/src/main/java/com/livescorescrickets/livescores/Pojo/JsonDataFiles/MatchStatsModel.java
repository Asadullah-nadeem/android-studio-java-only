package com.livescorescrickets.livescores.Pojo.JsonDataFiles;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class MatchStatsModel {
    @SerializedName("Matchst")
    @Expose
    private ArrayList<Entry> Matchst;
    @SerializedName(NotificationCompat.CATEGORY_MESSAGE)
    @Expose
    private String msg;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public ArrayList<Entry> getMatchst() {
        return this.Matchst;
    }

    public void setMatchst(ArrayList<Entry> arrayList) {
        this.Matchst = arrayList;
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

    public static class Entry implements Serializable {
        @SerializedName("matchname")
        @Expose
        private String matchname;
        @SerializedName("stat1descr")
        @Expose
        private String stat1descr;
        @SerializedName("stat1name")
        @Expose
        private String stat1name;
        @SerializedName("stat2descr")
        @Expose
        private String stat2descr;
        @SerializedName("stat2name")
        @Expose
        private String stat2name;
        @SerializedName("stat3descr")
        @Expose
        private String stat3descr;
        @SerializedName("stat3name")
        @Expose
        private String stat3name;

        public String getMatchname() {
            return this.matchname;
        }

        public void setMatchname(String str) {
            this.matchname = str;
        }

        public String getStat1name() {
            return this.stat1name;
        }

        public void setStat1name(String str) {
            this.stat1name = str;
        }

        public String getStat2name() {
            return this.stat2name;
        }

        public void setStat2name(String str) {
            this.stat2name = str;
        }

        public String getStat3name() {
            return this.stat3name;
        }

        public void setStat3name(String str) {
            this.stat3name = str;
        }

        public String getStat1descr() {
            return this.stat1descr;
        }

        public void setStat1descr(String str) {
            this.stat1descr = str;
        }

        public String getStat2descr() {
            return this.stat2descr;
        }

        public void setStat2descr(String str) {
            this.stat2descr = str;
        }

        public String getStat3descr() {
            return this.stat3descr;
        }

        public void setStat3descr(String str) {
            this.stat3descr = str;
        }
    }
}
