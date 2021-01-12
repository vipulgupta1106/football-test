package com.sapient.footballmatchtest.exceptionHandler;

import com.sapient.footballmatchtest.model.exception.PropagatingSystemException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class StandingsExceptionHandlerTest {
    private static StandingsExceptionHandler exceptionHandler;
    private static WebRequest request = mock(WebRequest.class);

    @BeforeAll
    public static void setUp() {
        when(request.getHeader(anyString())).thenReturn("Request_ID");
        exceptionHandler = new StandingsExceptionHandler();
    }

    @Test
    public void handleHttpClientErrorExceptionValidExceptionTest() {
        HttpClientErrorException clientErrorException = new HttpClientErrorException(
                HttpStatus.NOT_FOUND,
                "The resource you have requested is not available!");
        ResponseEntity responseEntity = exceptionHandler
                .handleHttpClientErrorException(clientErrorException, request);
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody()).isInstanceOf(PropagatingSystemException.class);
        assertThat(((PropagatingSystemException) responseEntity.getBody()).getHttpStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void handleGenericExceptionValidExceptionTest() {
        RuntimeException runtimeException = new RuntimeException("Generic error while processing!");
        ResponseEntity responseEntity = exceptionHandler
                .handleGenericException(runtimeException, request);
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody()).isInstanceOf(PropagatingSystemException.class);
        assertThat(((PropagatingSystemException) responseEntity.getBody()).getHttpStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}

