package com.global.exception;

public class NoSuchBoardIdException extends RuntimeException {
    public NoSuchBoardIdException(String message) {
        super(message);
    }
}
