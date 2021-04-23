package com.happypet.HappyPet.exception;

public class WrongCredentialsException extends HappypetApiException {
    public WrongCredentialsException(String message) {
        super(message);
    }
}