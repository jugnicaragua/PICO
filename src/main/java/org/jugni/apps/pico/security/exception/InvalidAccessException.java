package org.jugni.apps.pico.security.exception;

@SuppressWarnings("serial")
public class InvalidAccessException extends Exception {

  public InvalidAccessException() {
    super();
  }

  public InvalidAccessException(String message) {
    super(message);
  }
}
