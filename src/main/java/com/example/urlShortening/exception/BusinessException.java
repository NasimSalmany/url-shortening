package com.example.urlShortening.exception;

/**
 * @author Nasim Salmany
 */
public class BusinessException extends RuntimeException {

    private final String errorMessage;

    public BusinessException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
