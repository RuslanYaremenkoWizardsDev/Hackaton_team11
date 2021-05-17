package com.example.server.tournament.exception;

public class PoolIsEmpty extends RuntimeException {
    public PoolIsEmpty(String message) {
        super(message);
    }
}
