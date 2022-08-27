package com.livescorescrickets.livescores.Pojo;

public class PlayerDetails {
    String playerImage;
    String playerName;
    String playerRun;
    String playerSNo;
    String playerTeam;
    String playerWickets;

    public PlayerDetails() {
        this.playerName = "";
        this.playerImage = "";
    }

    public PlayerDetails(String str, String str2, String str3, String str4, String str5, String str6) {
        this.playerSNo = str;
        this.playerName = str2;
        this.playerImage = str3;
        this.playerTeam = str4;
        this.playerRun = str5;
        this.playerWickets = str6;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String str) {
        this.playerName = str;
    }

    public String getPlayerImage() {
        return this.playerImage;
    }

    public void setPlayerImage(String str) {
        this.playerImage = str;
    }

    public String getPlayerSNo() {
        return this.playerSNo;
    }

    public void setPlayerSNo(String str) {
        this.playerSNo = str;
    }

    public String getPlayerTeam() {
        return this.playerTeam;
    }

    public void setPlayerTeam(String str) {
        this.playerTeam = str;
    }

    public String getPlayerRun() {
        return this.playerRun;
    }

    public void setPlayerRun(String str) {
        this.playerRun = str;
    }

    public String getPlayerWickets() {
        return this.playerWickets;
    }

    public void setPlayerWickets(String str) {
        this.playerWickets = str;
    }
}
