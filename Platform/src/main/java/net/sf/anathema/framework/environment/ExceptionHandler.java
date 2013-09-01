package net.sf.anathema.framework.environment;

public interface ExceptionHandler {
  void handle(Throwable exception);

  void handle(Throwable exception, String message);
}