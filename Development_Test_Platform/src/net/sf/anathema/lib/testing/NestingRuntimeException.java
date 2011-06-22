package net.sf.anathema.lib.testing;

public class NestingRuntimeException extends RuntimeException {
  private static final long serialVersionUID = -9160789467075332936L;
  private final Exception exception;

  public NestingRuntimeException(Exception exception) {
    this.exception = exception;
  }

  @Override
  public Throwable getCause() {
    return exception;
  }
}