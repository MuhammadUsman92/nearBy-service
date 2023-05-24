package com.muhammadusman92.nearbyservice.exception;

public class FirebaseMessagingExceptionHandler extends RuntimeException{
    private String errorMessage;
    public FirebaseMessagingExceptionHandler(String errorMessage) {
        super(String.format("Firebase error is occure and error message: %s",errorMessage));
        this.errorMessage = errorMessage;
    }
}
