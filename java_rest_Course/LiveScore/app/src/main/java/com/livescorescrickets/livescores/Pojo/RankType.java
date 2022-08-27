package com.livescorescrickets.livescores.Pojo;

public class RankType {
    String matches;
    String points;
    String rank;
    String rankType;
    String rating;
    String team;

    public RankType(String str, String str2, String str3, String str4, String str5) {
        this.rank = str;
        this.team = str2;
        this.matches = str3;
        this.points = str4;
        this.rating = str5;
    }

    public String getTeam() {
        return this.team;
    }

    public void setTeam(String str) {
        this.team = str;
    }

    public String getMatches() {
        return this.matches;
    }

    public void setMatches(String str) {
        this.matches = str;
    }

    public String getPoints() {
        return this.points;
    }

    public void setPoints(String str) {
        this.points = str;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String str) {
        this.rating = str;
    }

    public String getRank() {
        return this.rank;
    }

    public void setRank(String str) {
        this.rank = str;
    }

    public String getRankType() {
        return this.rankType;
    }

    public void setRankType(String str) {
        this.rankType = str;
    }

    public RankType(String str) {
        this.rankType = str;
    }
}
