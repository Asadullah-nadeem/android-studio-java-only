package com.livescorescrickets.livescores.Pojo.MultiMatch;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GetUpcomingMatchesPojo {
    @SerializedName("AllMatch")
    @Expose
    private List<AllMatch> allMatch = null;
    @SerializedName(NotificationCompat.CATEGORY_MESSAGE)
    @Expose
    private String msg;
    @SerializedName("Playerslist")
    @Expose
    private Object playerslist;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public Object getPlayerslist() {
        return this.playerslist;
    }

    public void setPlayerslist(Object obj) {
        this.playerslist = obj;
    }

    public List<AllMatch> getAllMatch() {
        return this.allMatch;
    }

    public void setAllMatch(List<AllMatch> list) {
        this.allMatch = list;
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

    public static class AllMatch {
        @SerializedName("ImageUrl")
        @Expose
        private String imageUrl;
        @SerializedName("Matchtime")
        @Expose
        private String matchtime;
        @SerializedName("Result")
        @Expose
        private String result;
        @SerializedName("TeamA")
        @Expose
        private String teamA;
        @SerializedName("TeamAImage")
        @Expose
        private String teamAImage;
        @SerializedName("TeamB")
        @Expose
        private String teamB;
        @SerializedName("TeamBImage")
        @Expose
        private String teamBImage;
        @SerializedName("Title")
        @Expose
        private String title;
        @SerializedName("Venue")
        @Expose
        private String venue;

        public String getImageUrl() {
            return this.imageUrl;
        }

        public void setImageUrl(String str) {
            this.imageUrl = str;
        }

        public String getTeamA() {
            return this.teamA;
        }

        public void setTeamA(String str) {
            this.teamA = str;
        }

        public String getTeamB() {
            return this.teamB;
        }

        public void setTeamB(String str) {
            this.teamB = str;
        }

        public String getTeamAImage() {
            return this.teamAImage;
        }

        public void setTeamAImage(String str) {
            this.teamAImage = str;
        }

        public String getTeamBImage() {
            return this.teamBImage;
        }

        public void setTeamBImage(String str) {
            this.teamBImage = str;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getMatchtime() {
            return this.matchtime;
        }

        public void setMatchtime(String str) {
            this.matchtime = str;
        }

        public String getVenue() {
            return this.venue;
        }

        public void setVenue(String str) {
            this.venue = str;
        }

        public String getResult() {
            return this.result;
        }

        public void setResult(String str) {
            this.result = str;
        }
    }
}
