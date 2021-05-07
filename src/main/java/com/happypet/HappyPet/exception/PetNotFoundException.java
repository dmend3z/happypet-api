package com.happypet.HappyPet.exception;

public class PetNotFoundException extends HappypetApiException {

    public PetNotFoundException(String message) {
        super(message);
    }
}