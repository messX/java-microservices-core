package com.example.core.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.mapstruct.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitBreakerConfiguration {
    public final static String DEFAULT_CIRCUIT_BREAKER_REGISTRY = "DEFAULT_CIRCUIT_BREAKER_REGISTRY";

    private CircuitBreakerConfig getCircuitBreakerConfig()
    {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(10)
                .waitDurationInOpenState(Duration.ofMillis(30000))
                .permittedNumberOfCallsInHalfOpenState(2)
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(5)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                //.recordExceptions(CheckAvailabilityException.class)
                .build();

    }

    @Bean
    //@Qualifier(DEFAULT_CIRCUIT_BREAKER_REGISTRY)
    public CircuitBreakerRegistry getCircuitBreakerRegistry()
    {
        return CircuitBreakerRegistry.of(getCircuitBreakerConfig());
    }
}
