package com.happypet.HappyPet.exception;

public class PetAlreadyExistsException extends RuntimeException{
    public PetAlreadyExistsException(String message) {
        super(message);
    }
}
