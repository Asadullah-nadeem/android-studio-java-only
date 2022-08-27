package com.livescorescrickets.livescores.Pojo;

public class MatchFilterData {
    String team1Name;
    private boolean texthold;

    public String getTeam1Name() {
        return this.team1Name;
    }

    public void setTeam1Name(String str) {
        this.team1Name = str;
    }

    public boolean isTexthold() {
        return this.texthold;
    }

    public void setTexthold(boolean z) {
        this.texthold = z;
    }
}
