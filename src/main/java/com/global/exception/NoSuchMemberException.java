package com.global.exception;

public class NoSuchMemberException extends RuntimeException {
  public NoSuchMemberException(String message) {
    super(message);
  }
}
