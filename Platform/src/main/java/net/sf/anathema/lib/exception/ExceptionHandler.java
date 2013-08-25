package net.sf.anathema.lib.exception;

public interface ExceptionHandler {
  void handle(Throwable exception);

  void handle(Throwable exception, String message);
}