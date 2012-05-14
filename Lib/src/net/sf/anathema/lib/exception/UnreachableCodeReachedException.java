package net.sf.anathema.lib.exception;

public class UnreachableCodeReachedException extends AnathemaException {

  public UnreachableCodeReachedException() {
    super();
  }

  public UnreachableCodeReachedException(String s) {
    super(s);
  }

  public UnreachableCodeReachedException(Throwable nestedException) {
    super(nestedException);
  }

  public UnreachableCodeReachedException(String message, Throwable nestedException) {
    super(message, nestedException);
  }
}