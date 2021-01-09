package com.sapient.footballmatchtest.service;

import com.sapient.footballmatchtest.gateway.DataGateway;
import com.sapient.footballmatchtest.model.Leagues;
import com.sapient.footballmatchtest.model.StandingsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StandingsService {
    private static final Logger log = LoggerFactory.getLogger(StandingsService.class);

    private DataGateway dataGateway;

    @Autowired
    public StandingsService(
            DataGateway dataGateway) {
        this.dataGateway = dataGateway;
    }

    public List<Leagues> getAllLeagues() {
        return dataGateway.getAllLeagues();
    }

    public List<StandingsDTO> getAllStandings(List<Integer> leagueIdList) {
        List<StandingsDTO> finalStandings = new ArrayList<>();
        for(Integer league : leagueIdList){
            finalStandings.addAll(dataGateway.getStandings(league));
        }
        return finalStandings;
    }
}
