package com.example.server.tournament.exception;

public class TournamentWithSameNameExist extends RuntimeException{
    public TournamentWithSameNameExist(String message){
        super(message);
    }
}
