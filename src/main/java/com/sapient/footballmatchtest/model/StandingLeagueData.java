package com.sapient.footballmatchtest.model;

import java.util.List;

public class StandingLeagueData {

    private List<Leagues> leagues;
    private List<StandingsDTO> standingsDTO;

    public StandingLeagueData(List<Leagues> leagues, List<StandingsDTO> standingsDTO) {
        this.leagues = leagues;
        this.standingsDTO = standingsDTO;
    }

    public List<Leagues> getLeagues() {
        return leagues;
    }

    public List<StandingsDTO> getStandingsDTO() {
        return standingsDTO;
    }
}
