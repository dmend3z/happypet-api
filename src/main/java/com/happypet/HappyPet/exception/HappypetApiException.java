package com.happypet.HappyPet.exception;

public class HappypetApiException extends RuntimeException {
    public HappypetApiException() {
    }

    public HappypetApiException(String message) {
        super(message);
    }

    public HappypetApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public HappypetApiException(Throwable cause) {
        super(cause);
    }

    public HappypetApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
