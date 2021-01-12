package com.sapient.footballmatchtest.service;

import com.sapient.footballmatchtest.TestUtils;
import com.sapient.footballmatchtest.model.StandingLeagueData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StandingsOrchestratorTest {
    private static StandingsOrchestrator standingsOrchestrator;
    private static StandingLeagueData standingLeagueData;

    @BeforeAll
    public static void setup(){
        StandingsService standingsService = mock(StandingsService.class);
        standingLeagueData = mock(StandingLeagueData.class);
        standingsOrchestrator = Mockito.spy(new StandingsOrchestrator(standingsService));
        when(standingsService.getAllLeagues()).thenReturn(TestUtils.getLeagues());
        when(standingsService.getAllStandings(any())).thenReturn(TestUtils.getStandingsDTO());
        //when(standingsOrchestrator.makeStandingLeagueDataObject(any(),any())).thenReturn(mock(StandingLeagueData.class));
    }

    @Test
    public void transformToFinalOutputTest(){
        assertThat(standingsOrchestrator.transformToFinalOutput(TestUtils.getStandingsLeagueData())).hasSize(1);
    }

    @Test
    public void transformToFilteredOutputTestInvalid(){
        assertThrows(RuntimeException.class, () ->standingsOrchestrator.transformToFilteredOutput(TestUtils.getStandingsLeagueData(),"name","name","name"));
    }

    @Test
    public void transformToFilteredOutputTestValid(){
        assertThat(standingsOrchestrator.transformToFilteredOutput(TestUtils.getStandingsLeagueData(),"Country","League","Team"));
    }

    @Test
    public void getRawDataTest(){
        Mockito.doReturn(standingLeagueData).when(standingsOrchestrator).makeStandingLeagueDataObject(any(),any());
        assertThat(standingsOrchestrator.getRawData()).isNotNull();
    }
}
