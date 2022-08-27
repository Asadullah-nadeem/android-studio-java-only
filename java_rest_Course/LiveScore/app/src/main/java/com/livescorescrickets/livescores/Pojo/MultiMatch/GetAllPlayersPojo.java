package com.livescorescrickets.livescores.Pojo.MultiMatch;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GetAllPlayersPojo {
    @SerializedName(NotificationCompat.CATEGORY_MESSAGE)
    @Expose
    private String msg;
    @SerializedName("Playerslist")
    @Expose
    private List<Playerslist> playerslist = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<Playerslist> getPlayerslist() {
        return this.playerslist;
    }

    public void setPlayerslist(List<Playerslist> list) {
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

    public static class Playerslist {
        @SerializedName("Balls")
        @Expose
        private Integer balls;
        @SerializedName("four")
        @Expose
        private Integer four;
        @SerializedName("inning")
        @Expose
        private Integer inning;
        @SerializedName("isnotout")
        @Expose
        private Integer isnotout;
        @SerializedName("outby")
        @Expose
        private String outby;
        @SerializedName("PlayerImage")
        @Expose
        private String playerImage;
        @SerializedName("PlayerName")
        @Expose
        private String playerName;
        @SerializedName("Runs")
        @Expose
        private Integer runs;
        @SerializedName("seqno")
        @Expose
        private Integer seqno;
        @SerializedName("six")
        @Expose
        private Integer six;
        @SerializedName("TeamName")
        @Expose
        private String teamName;
        @SerializedName("TeamRuns")
        @Expose
        private String teamRuns;
        @SerializedName("TeamSide")
        @Expose
        private String teamSide;

        public Integer getInning() {
            return this.inning;
        }

        public void setInning(Integer num) {
            this.inning = num;
        }

        public String getOutby() {
            return this.outby;
        }

        public void setOutby(String str) {
            this.outby = str;
        }

        public String getTeamName() {
            return this.teamName;
        }

        public void setTeamName(String str) {
            this.teamName = str;
        }

        public String getPlayerName() {
            return this.playerName;
        }

        public void setPlayerName(String str) {
            this.playerName = str;
        }

        public String getTeamRuns() {
            return this.teamRuns;
        }

        public void setTeamRuns(String str) {
            this.teamRuns = str;
        }

        public String getPlayerImage() {
            return this.playerImage;
        }

        public void setPlayerImage(String str) {
            this.playerImage = str;
        }

        public Integer getRuns() {
            return this.runs;
        }

        public void setRuns(Integer num) {
            this.runs = num;
        }

        public String getTeamSide() {
            return this.teamSide;
        }

        public void setTeamSide(String str) {
            this.teamSide = str;
        }

        public Integer getBalls() {
            return this.balls;
        }

        public void setBalls(Integer num) {
            this.balls = num;
        }

        public Integer getFour() {
            return this.four;
        }

        public void setFour(Integer num) {
            this.four = num;
        }

        public Integer getSix() {
            return this.six;
        }

        public void setSix(Integer num) {
            this.six = num;
        }

        public Integer getSeqno() {
            return this.seqno;
        }

        public void setSeqno(Integer num) {
            this.seqno = num;
        }

        public Integer getIsnotout() {
            return this.isnotout;
        }

        public void setIsnotout(Integer num) {
            this.isnotout = num;
        }
    }
}
