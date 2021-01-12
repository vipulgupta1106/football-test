package com.sapient.footballmatchtest.gateway;

import com.sapient.footballmatchtest.TestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DataGatewayTest {
    private static DataGateway dataGateway;
    private static ResponseEntity responseEntity;

    @BeforeAll
    public static void setup() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        String apiKey = "823104eudijoewfk";
        String leaguesDataExtensionPath = "/leagues/data/extension/path";
        String apiKeyExtensionPath = "/api/key/extension/path";
        String baseAPI = "http://base/api";
        String standingsDataExtensionPath = "/standings/data/extension/path";
        String leagueIdFilter = "leagueId=";
        dataGateway = new DataGateway(restTemplate, baseAPI, apiKey, leaguesDataExtensionPath, apiKeyExtensionPath, standingsDataExtensionPath, leagueIdFilter);
        responseEntity = mock(ResponseEntity.class);
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(),
                any(ParameterizedTypeReference.class))).thenReturn(responseEntity);
    }

    @Test
    public void getStandingsTest() {
        when(responseEntity.getBody())
                .thenReturn(Collections.singletonList(TestUtils.getStandingsDTO()));
        assertThat(dataGateway.getStandings(1)).isNotNull();
    }

    @Test
    public void getAllLeaguesTest() {
        when(responseEntity.getBody())
                .thenReturn(Collections.singletonList(TestUtils.getLeagues()));
        assertThat(dataGateway.getAllLeagues()).isNotNull();
    }
}
