package com.example.server.exception;

public class InvalidTimeStart extends RuntimeException{
    public InvalidTimeStart(String message){
        super(message);
    }
}
