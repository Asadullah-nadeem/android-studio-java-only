package com.livescorescrickets.livescores.Pojo;

public class TeamRanking {
    String TeamPoints;
    String imageTeam;
    String nameTeam;
    String teamMatches;
    String teamRanking;

    public String getImageTeam() {
        return this.imageTeam;
    }

    public void setImageTeam(String str) {
        this.imageTeam = str;
    }

    public String getNameTeam() {
        return this.nameTeam;
    }

    public void setNameTeam(String str) {
        this.nameTeam = str;
    }

    public String getTeamMatches() {
        return this.teamMatches;
    }

    public void setTeamMatches(String str) {
        this.teamMatches = str;
    }

    public String getTeamPoints() {
        return this.TeamPoints;
    }

    public void setTeamPoints(String str) {
        this.TeamPoints = str;
    }

    public String getTeamRanking() {
        return this.teamRanking;
    }

    public void setTeamRanking(String str) {
        this.teamRanking = str;
    }

    public TeamRanking(String str, String str2, String str3, String str4, String str5) {
        this.imageTeam = str;
        this.nameTeam = str2;
        this.teamMatches = str3;
        this.TeamPoints = str4;
        this.teamRanking = str5;
    }
}
