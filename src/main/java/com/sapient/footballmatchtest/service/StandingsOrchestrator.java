package com.sapient.footballmatchtest.service;

import com.sapient.footballmatchtest.model.Leagues;
import com.sapient.footballmatchtest.model.StandingLeagueData;
import com.sapient.footballmatchtest.model.StandingRow;
import com.sapient.footballmatchtest.model.StandingsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StandingsOrchestrator {
    private static final Logger log = LoggerFactory.getLogger(StandingsOrchestrator.class);

    private StandingsService standingsService;

    @Autowired
    public StandingsOrchestrator(
            StandingsService standingsService) {
        this.standingsService = standingsService;
    }

    public StandingLeagueData getRawData() {
        log.info("Fetching leagues data");
        List<Leagues> leagues = standingsService.getAllLeagues();
        List<Integer> leagueIdList = leagues.stream().map(p -> Integer.parseInt(p.getLeagueId())).collect(Collectors.toList());
        log.info("Fetching standings for all leagues");
        List<StandingsDTO> standingsDTOS = standingsService.getAllStandings(leagueIdList);
        return new StandingLeagueData(leagues, standingsDTOS);
    }

    public List<StandingRow> getAllTeamsStandings() {
        return transformToFinalOutput(getRawData());
    }

    public List<StandingRow> getFilteredStandings(String countryName, String leagueName, String teamName) {
        return transformToFilteredOutput(getRawData(), countryName, leagueName, teamName);
    }


    public List<StandingRow> transformToFinalOutput(StandingLeagueData standingLeagueData) {
        List<StandingRow> standingRowList = new ArrayList<>();
        for (StandingsDTO dto : standingLeagueData.getStandingsDTO()) {
            StandingRow standingRow = new StandingRow();
            // Assuming that Country id and name mapping will be correct.
            standingRow.setCountryInfo(standingLeagueData.getLeagues().stream().filter(l -> l.getCountryName().equalsIgnoreCase(dto.getCountryName())).map(l -> l.getCountryId()).findFirst().get() + " - " + dto.getCountryName());
            standingRow.setLeagueInfo(dto.getLeagueId() + " - " + dto.getLeagueName());
            standingRow.setTeamInfo(dto.getTeamId() + " - " + dto.getTeamName());
            standingRow.setLeaguePosition(dto.getOverallLeaguePosition());
            standingRowList.add(standingRow);
        }
        return standingRowList;
    }

    //Return type is List -- considered that for the given combination , there can be more than one result.
    public List<StandingRow> transformToFilteredOutput(StandingLeagueData standingLeagueData, String countryName, String leagueName, String teamName) {
        List<StandingsDTO> filteredStandings = standingLeagueData.getStandingsDTO().stream().filter(p -> p.getCountryName().equalsIgnoreCase(countryName)
                && p.getLeagueName().equalsIgnoreCase(leagueName)
                && p.getTeamName().equalsIgnoreCase(teamName)).collect(Collectors.toList());
        standingLeagueData.setStandingsDTO(filteredStandings);
        if ((filteredStandings.size() > 0)) {
            return transformToFinalOutput(standingLeagueData);
        }
        throw new RuntimeException("No team found for this combination");
    }
}
