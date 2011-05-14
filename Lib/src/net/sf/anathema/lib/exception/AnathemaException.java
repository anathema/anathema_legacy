package net.sf.anathema.lib.exception;

public class AnathemaException extends Exception {

  private static final long serialVersionUID = 2532438089327188115L;

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