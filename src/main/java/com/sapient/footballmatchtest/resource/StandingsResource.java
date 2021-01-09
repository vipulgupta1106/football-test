package com.sapient.footballmatchtest.resource;

import com.sapient.footballmatchtest.model.StandingRow;
import com.sapient.footballmatchtest.model.exception.PropagatingSystemException;
import com.sapient.footballmatchtest.service.StandingsOrchestrator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Team-Standings-Service", description = "Operations to calculate Team's standings")
public class StandingsResource {

    private static final Logger log = LoggerFactory.getLogger(StandingsResource.class);
    private StandingsOrchestrator standingsOrchestrator;

    @Autowired
    public StandingsResource(
            StandingsOrchestrator standingsOrchestrator) {
        this.standingsOrchestrator = standingsOrchestrator;
    }

    @GetMapping(path = "/getAllTeamsStandings", produces = "application/json")
    @ApiOperation(value = "Get all Team's Standings in different leagues", response = StandingRow.class, responseContainer = "list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched data from APIs", response = StandingRow.class, responseContainer = "list"),
            @ApiResponse(code = 401, message = "Unauthorized access to API", response = PropagatingSystemException.class),
            @ApiResponse(code = 500, message = "Internal server error", response = PropagatingSystemException.class)})
    public List<StandingRow> getAllTeamsStandings() {
        log.info("Getting all teams Standings");
        List<StandingRow> standingRowList =  standingsOrchestrator.getAllTeamsStandings();
        log.info("Successfully fetched the standings of all teams in different leagues");
        return standingRowList;
    }

    @GetMapping(path = "/getFilteredStandings/{countryName}/{leagueName}/{teamName}", produces = "application/json")
    @ApiOperation(value = "Get Team's standings based on Country and League", response = StandingRow.class, responseContainer = "list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched data from APIs", response = StandingRow.class, responseContainer = "list"),
            @ApiResponse(code = 401, message = "Unauthorized access to API", response = PropagatingSystemException.class),
            @ApiResponse(code = 500, message = "Internal server error", response = PropagatingSystemException.class)})
    public List<StandingRow> getFilteredStandings(@PathVariable String countryName, @PathVariable String leagueName, @PathVariable String teamName) {
        log.info("Getting teams Standings based upon country and league name");
        List<StandingRow> standingRowList = standingsOrchestrator.getFilteredStandings(countryName, leagueName, teamName);
        log.info("Successfully fetched the standings of team");
        return standingRowList;
    }
}
