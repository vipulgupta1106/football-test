package com.sapient.footballmatchtest;

import com.sapient.footballmatchtest.model.Leagues;
import com.sapient.footballmatchtest.model.StandingLeagueData;
import com.sapient.footballmatchtest.model.StandingRow;
import com.sapient.footballmatchtest.model.StandingsDTO;

import java.util.Arrays;
import java.util.List;

public class TestUtils {
    public static List<StandingsDTO> getStandingsDTO(){
        StandingsDTO standingsDTO = new StandingsDTO();
        standingsDTO.setCountryName("Country");
        standingsDTO.setLeagueId("leagueId");
        standingsDTO.setLeagueName("League");
        standingsDTO.setOverallLeaguePosition(1);
        standingsDTO.setTeamId("1");
        standingsDTO.setTeamName("Team");
        return Arrays.asList(standingsDTO);
    }

    public static List<Leagues> getLeagues(){
        Leagues leagues = new Leagues();
        leagues.setCountryId(1);
        leagues.setCountryName("Country");
        leagues.setLeagueId("2");
        leagues.setLeagueName("League");
        return Arrays.asList(leagues);
    }

    public static List<StandingRow> getStandingRow() {
        StandingRow standingRow = new StandingRow();
        standingRow.setCountryInfo("Country id - name");
        standingRow.setLeagueInfo("League id - name");
        standingRow.setTeamInfo("Team id - name ");
        standingRow.setLeaguePosition(1);
        return Arrays.asList(standingRow);
    }

    public static StandingLeagueData getStandingsLeagueData() {
        return new StandingLeagueData(getLeagues(),getStandingsDTO());
    }
}
