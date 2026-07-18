package com.digitalbanking.exceptions;

public class FunctionalError extends RuntimeException {
    public FunctionalError(String message) {
        super(message);
    }
}
