package com.example.server.exception;

public class TournamentNotFoundException extends RuntimeException{
    public TournamentNotFoundException(String message) {
        super(message);
    }
}
