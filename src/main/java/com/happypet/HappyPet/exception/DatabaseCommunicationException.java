package com.happypet.HappyPet.exception;

public class DatabaseCommunicationException extends HappypetApiException {

    public DatabaseCommunicationException(String message, Throwable cause) {
        super(message, cause);
    }
}