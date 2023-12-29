package com.example.core.test.exception;

public class CircuitBreakerDataException extends RuntimeException{
    public CircuitBreakerDataException(String message) {
        super(message);
    }
}
