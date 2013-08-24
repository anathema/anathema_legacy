package net.sf.anathema.lib.exception;

public class ConsoleExceptionHandler implements ExceptionHandler {
  @Override
  public void handle(Throwable exception) {
    exception.printStackTrace();
  }
}