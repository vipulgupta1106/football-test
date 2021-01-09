package com.sapient.footballmatchtest.resource;

import com.sapient.footballmatchtest.model.StandingRow;
import com.sapient.footballmatchtest.service.StandingsOrchestrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class StandingsResource {

    private static final Logger log = LoggerFactory.getLogger(StandingsResource.class);
    private StandingsOrchestrator standingsOrchestrator;

    @Autowired
    public StandingsResource(
            StandingsOrchestrator standingsOrchestrator) {
        this.standingsOrchestrator = standingsOrchestrator;
    }
    @GetMapping(path = "/getAllTeamsStandings",produces = "application/json")
    public List<StandingRow> getAllTeamsStandings(){
        log.info("Geting teams Standings");
        return standingsOrchestrator.getAllTeamsStandings();
    }

    @GetMapping(path = "/getFilteredStandings/{countryName}/{leagueName}/{teamName}",produces = "application/json")
    public List<StandingRow> getFilteredStandings(@PathVariable String countryName, @PathVariable String leagueName,@PathVariable String teamName){
        log.info("Geting teams Standings");
        return standingsOrchestrator.getFilteredStandings(countryName,leagueName,teamName);
    }
}
