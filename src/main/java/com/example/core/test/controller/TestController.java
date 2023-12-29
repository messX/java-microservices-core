package com.example.core.test.controller;


import com.example.core.test.service.TestService;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("${base.url}/test")
public class TestController {
    @Autowired
    TestService service;
    @Value("${circuitbreaker.testname}")
    private String CIRCUIT_BREAKER;

    // test url for testing circuitbreaking
    @GetMapping(name = "get_circuit_breaker_data", path = "/get_circuit_breaker_data")
    public Object getCircuitBreakerData(){
        log.info("Testing circuit breaking");
        String res = "";
        try {
            res = service.getCircuitBreakerData();
        }
        catch (RuntimeException ex){
            log.error("Error in fetching :: ", ex.getMessage());
//            CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults(CIRCUIT_BREAKER);
//            log.info(String.format("Circuit breaker state %s", circuitBreaker.getState().name()));
            return ResponseEntity.internalServerError();
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping(name = "temp_circuit_breaker", path = "/temp_circuit_breaker")
    public Object tempCircuitBreaker(){
        log.info("Temp circuit breaking");

        return ResponseEntity.internalServerError();
    }

}
