package com.livescorescrickets.livescores.Pojo.JsonDataFiles;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class MatchResultModel implements Serializable {
    @SerializedName("AllMatch")
    @Expose
    private List<AllMatch> allMatch;
    @SerializedName(NotificationCompat.CATEGORY_MESSAGE)
    @Expose
    private String msg;
    @SerializedName("success")
    @Expose
    private Boolean success;

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

    public static class AllMatch implements Serializable {
        @SerializedName("Matchtype")
        @Expose
        private String Matchtype;
        @SerializedName("Result")
        @Expose
        private String Result;
        @SerializedName("ImageUrl")
        @Expose
        private String imageUrl;
        @SerializedName("MatchId")
        @Expose
        private Integer matchId;
        @SerializedName("Matchtime")
        @Expose
        private String matchtime;
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

        public String getMatchtype() {
            return this.Matchtype;
        }

        public void setMatchtype(String str) {
            this.Matchtype = str;
        }

        public String getResult() {
            return this.Result;
        }

        public void setResult(String str) {
            this.Result = str;
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

        public Integer getMatchId() {
            return this.matchId;
        }

        public void setMatchId(Integer num) {
            this.matchId = num;
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

        public String getImageUrl() {
            return this.imageUrl;
        }

        public void setImageUrl(String str) {
            this.imageUrl = str;
        }
    }
}
