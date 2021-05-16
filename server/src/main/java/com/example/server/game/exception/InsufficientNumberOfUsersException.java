package com.example.server.game.exception;

public class InsufficientNumberOfUsersException extends RuntimeException{
    public InsufficientNumberOfUsersException(String message){
        super(message);
    }
}
