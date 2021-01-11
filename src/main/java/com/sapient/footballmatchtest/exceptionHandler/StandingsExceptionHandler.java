package com.sapient.footballmatchtest.exceptionHandler;

import java.util.UUID;
import com.sapient.footballmatchtest.model.exception.PropagatingSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class StandingsExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(StandingsExceptionHandler.class);

    @ExceptionHandler({HttpClientErrorException.class})
    public ResponseEntity<PropagatingSystemException> handleHttpClientErrorException(
            HttpClientErrorException exception,
            WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
        PropagatingSystemException propagatingSystemException = new PropagatingSystemException();
        propagatingSystemException.setDebugId(UUID.randomUUID().toString());
        propagatingSystemException.setHttpStatus(exception.getStatusCode().getReasonPhrase());
        propagatingSystemException.setHttpStatusCode(exception.getRawStatusCode());
        propagatingSystemException.setMessage(exception.getMessage());
        LOG.error(exception.getLocalizedMessage(), exception);
        return new ResponseEntity<>(propagatingSystemException, headers, exception.getStatusCode());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<PropagatingSystemException> handleGenericException(Exception exception,
                                                                             WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
        PropagatingSystemException propagatingSystemException = new PropagatingSystemException();
        propagatingSystemException.setDebugId(UUID.randomUUID().toString());
        propagatingSystemException.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        propagatingSystemException.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        propagatingSystemException.setMessage(exception.getMessage());
        LOG.error(exception.getLocalizedMessage(), exception);
        return new ResponseEntity<>(propagatingSystemException, headers,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
