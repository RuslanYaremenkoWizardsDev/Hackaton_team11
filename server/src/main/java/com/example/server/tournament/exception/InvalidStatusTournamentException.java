package com.example.server.tournament.exception;

public class InvalidStatusTournamentException extends RuntimeException {
    public InvalidStatusTournamentException(String message) {
        super(message);
    }
}
