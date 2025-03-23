package io.global.exception;

public class NoSuchBoardNameException extends RuntimeException {
    public NoSuchBoardNameException(String message) {
        super(message);
    }

    public NoSuchBoardNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchBoardNameException(Throwable cause) {
        super(cause);
    }

    protected NoSuchBoardNameException(String message, Throwable cause, boolean enableSuppression,
                                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
