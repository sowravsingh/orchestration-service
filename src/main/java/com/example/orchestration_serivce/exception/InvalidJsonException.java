package com.example.orchestration_serivce.exception;

public class InvalidJsonException extends RuntimeException{

    public InvalidJsonException(String message) {
        super(message);
    }
}
