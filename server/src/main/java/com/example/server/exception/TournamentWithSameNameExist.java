package com.example.server.exception;

public class TournamentWithSameNameExist extends RuntimeException{
    public TournamentWithSameNameExist(String message){
        super(message);
    }
}
