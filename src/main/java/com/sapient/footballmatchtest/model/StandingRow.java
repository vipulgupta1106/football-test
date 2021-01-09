package com.sapient.footballmatchtest.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class StandingRow {
    @JsonProperty("Country Details")
    private String countryInfo;

    @JsonProperty("League Details")
    private String leagueInfo;

    @JsonProperty("Team Details")
    private String teamInfo;

    @JsonProperty("League Position")
    private int leaguePosition;

    public String getCountryInfo() {
        return countryInfo;
    }

    public void setCountryInfo(String countryInfo) {
        this.countryInfo = countryInfo;
    }

    public String getLeagueInfo() {
        return leagueInfo;
    }

    public void setLeagueInfo(String leagueInfo) {
        this.leagueInfo = leagueInfo;
    }

    public String getTeamInfo() {
        return teamInfo;
    }

    public void setTeamInfo(String teamInfo) {
        this.teamInfo = teamInfo;
    }

    public int getLeaguePosition() {
        return leaguePosition;
    }

    public void setLeaguePosition(int leaguePosition) {
        this.leaguePosition = leaguePosition;
    }
}
