package com.global.exception;

public class DuplicateSignInException extends RuntimeException {
    public DuplicateSignInException(String message) {
        super(message);
    }
}
