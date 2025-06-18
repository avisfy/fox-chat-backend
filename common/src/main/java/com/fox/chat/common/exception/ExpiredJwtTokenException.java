package com.fox.chat.common.exception;

public class ExpiredJwtTokenException extends RuntimeException {
  public ExpiredJwtTokenException() {
    super("Token expired");
  }
}
