package net.sf.anathema.lib.exception;

public class AnathemaException extends Exception {

  public AnathemaException() {
    super();
  }

  public AnathemaException(String message) {
    super(message);
  }

  public AnathemaException(String message, Throwable cause) {
    super(message, cause);
  }

  public AnathemaException(Throwable cause) {
    super(cause);
  }
}