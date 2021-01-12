package com.sapient.footballmatchtest.resource;

import com.sapient.footballmatchtest.TestUtils;
import com.sapient.footballmatchtest.service.StandingsOrchestrator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StandingsResourceTest {
    private static StandingsResource standingsResource;

    @BeforeAll
    public static void setup() {
        StandingsOrchestrator standingsOrchestrator = mock(StandingsOrchestrator.class);
        standingsResource = new StandingsResource(standingsOrchestrator);
        when(standingsOrchestrator.getAllTeamsStandings()).thenReturn(TestUtils.getStandingRow());
        when(standingsOrchestrator.getFilteredStandings(anyString(), anyString(), anyString())).thenReturn(TestUtils.getStandingRow());
    }

    @Test
    public void getAllTeamsStandingsTest() {
        assertThat(standingsResource.getAllTeamsStandings()).isNotNull().hasSize(1);
    }

    @Test
    public void getFilteredStandingsTest() {
        assertThat(standingsResource.getFilteredStandings("Country", "League", "Team")).isNotNull().hasSize(1);
    }

}
