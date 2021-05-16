package com.example.server.game.exception;

public class UserWasRegisterInThisTournament extends RuntimeException {
    public UserWasRegisterInThisTournament(String s) {
        super(s);
    }
}
