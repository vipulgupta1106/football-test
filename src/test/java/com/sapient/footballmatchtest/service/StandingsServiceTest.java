package com.sapient.footballmatchtest.service;

import com.sapient.footballmatchtest.TestUtils;
import com.sapient.footballmatchtest.gateway.DataGateway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StandingsServiceTest {
    private static StandingsService standingsService;

    @BeforeAll
    public static void setup() {
        DataGateway dataGateway = mock(DataGateway.class);
        standingsService = new StandingsService(dataGateway);
        when(dataGateway.getAllLeagues()).thenReturn(TestUtils.getLeagues());
        when(dataGateway.getStandings(any())).thenReturn(TestUtils.getStandingsDTO());
    }

    @Test
    public void getAllLeaguesTest() {
        assertThat(standingsService.getAllLeagues()).isNotNull().hasSize(1);
    }

    @Test
    public void getAllStandingsTest() {
        assertThat(standingsService.getAllStandings(Arrays.asList(1, 2, 3))).isNotNull().hasSize(3);
    }
}
