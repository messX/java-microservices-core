package com.example.core.test.service;

import com.example.core.test.exception.CircuitBreakerDataException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class TestService {

    private static String REMOTE_API_URL = "http://localhost:8081/api/v1/test/temp_circuit_breaker";

    @CircuitBreaker(name = "myProjectAllRemoteCallsCB", fallbackMethod = "getAPIFallBack")
    public String getCircuitBreakerData() throws CircuitBreakerDataException {
        String resp = "";
        try {
            RestTemplate restTemplate = new RestTemplate();
            resp = restTemplate.getForObject(REMOTE_API_URL, String.class);
        } catch (HttpClientErrorException | HttpServerErrorException ex){
            throw new CircuitBreakerDataException(ex.getMessage());
        }
        return resp;
    }

    public String getAPIFallBack(Exception e){
        log.error("Get api fallback circuit breaker error:: {}", e);
        return "";
    }
}
