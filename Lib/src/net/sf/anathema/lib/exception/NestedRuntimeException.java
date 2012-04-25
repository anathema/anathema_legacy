package net.sf.anathema.lib.exception;

public class NestedRuntimeException extends RuntimeException {

  public NestedRuntimeException(Throwable cause) {
    super(cause);
  }
}