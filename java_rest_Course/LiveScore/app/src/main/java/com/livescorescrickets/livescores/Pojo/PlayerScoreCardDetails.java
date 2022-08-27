package com.livescorescrickets.livescores.Pojo;

public class PlayerScoreCardDetails {
    String player4;
    String player6;
    String playerBalls;
    String playerName;
    String playerRun;
    String playerSNo;
    String playerSR;

    public PlayerScoreCardDetails() {
        this.playerSNo = "";
        this.playerName = "";
        this.playerRun = "";
        this.playerBalls = "";
        this.player4 = "";
        this.player6 = "";
        this.playerSR = "";
    }

    public PlayerScoreCardDetails(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.playerSNo = str;
        this.playerName = str2;
        this.playerRun = str3;
        this.playerBalls = str4;
        this.player4 = str5;
        this.player6 = str6;
        this.playerSR = str7;
    }

    public String getPlayerSNo() {
        return this.playerSNo;
    }

    public void setPlayerSNo(String str) {
        this.playerSNo = str;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String str) {
        this.playerName = str;
    }

    public String getPlayerRun() {
        return this.playerRun;
    }

    public void setPlayerRun(String str) {
        this.playerRun = str;
    }

    public String getPlayerBalls() {
        return this.playerBalls;
    }

    public void setPlayerBalls(String str) {
        this.playerBalls = str;
    }

    public String getPlayer4() {
        return this.player4;
    }

    public void setPlayer4(String str) {
        this.player4 = str;
    }

    public String getPlayer6() {
        return this.player6;
    }

    public void setPlayer6(String str) {
        this.player6 = str;
    }

    public String getPlayerSR() {
        return this.playerSR;
    }

    public void setPlayerSR(String str) {
        this.playerSR = str;
    }
}
