package com.sapient.footballmatchtest.gateway;

import com.sapient.footballmatchtest.model.Leagues;
import com.sapient.footballmatchtest.model.StandingsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class DataGateway {

    private static final Logger log = LoggerFactory.getLogger(DataGateway.class);

    private final RestTemplate restTemplate;
    private final String apiKey;
    private final String leaguesDataExtensionPath;
    private final String apiKeyExtensionPath;
    private final String baseAPI;
    private final String standingsDataExtensionPath;
    private final String leagueIdFilter;

    public DataGateway(RestTemplate restTemplate, @Value("${application.baseURL}") String baseAPI,
                       @Value("${application.apiKey}") String apiKey,
                       @Value("${application.leaguesDataExtensionPath}") String leaguesDataExtensionPath,
                       @Value("${application.apiKeyExtensionPath}") String apiKeyExtensionPath,
                       @Value("${application.standingsDataExtensionPath}") String standingsDataExtensionPath,
                       @Value("${application.leagueIdFilter}") String leagueIdFilter
    ) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.apiKeyExtensionPath = apiKeyExtensionPath;
        this.leaguesDataExtensionPath = leaguesDataExtensionPath;
        this.baseAPI = baseAPI;
        this.standingsDataExtensionPath = standingsDataExtensionPath;
        this.leagueIdFilter = leagueIdFilter;
    }

    public List<Leagues> getAllLeagues() {
        String finalURL = baseAPI + leaguesDataExtensionPath + apiKeyExtensionPath + apiKey;
        log.info(" Final API URL : " + finalURL);
        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(finalURL);
        return exchangeForListData(builder.toUriString(), HttpMethod.GET, new ParameterizedTypeReference<List<Leagues>>() {
        });
    }

    public List<StandingsDTO> getStandings(Integer league) {
        String finalURL = baseAPI + standingsDataExtensionPath + leagueIdFilter + league + apiKeyExtensionPath + apiKey;
        log.info(" Final API URL : " + finalURL);
        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(finalURL);
        return exchangeForListData(builder.toUriString(), HttpMethod.GET, new ParameterizedTypeReference<List<StandingsDTO>>() {
        });
    }

    private <T> List<T> exchangeForListData(String uri, HttpMethod httpMethod, ParameterizedTypeReference<List<T>> responseType) {
        return restTemplate.exchange(uri, httpMethod, null, responseType).getBody();
    }

}
