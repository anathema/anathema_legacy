package net.sf.anathema.lib.exception;

public class NestedRuntimeException extends RuntimeException {

  private static final long serialVersionUID = -8577610491522718822L;

public NestedRuntimeException(Throwable cause) {
    super(cause);
  }
}