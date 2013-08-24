package net.sf.anathema.lib.exception;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.List;

public class CentralExceptionHandling {

  private List<ExceptionHandler> handlers = new ArrayList<>();

  public CentralExceptionHandling() {
    attachForThreadUncaughtExceptionHandling();
  }

  public void addHandler(ExceptionHandler handler) {
    handlers.add(handler);
  }

  public void handle(Throwable exception) {
    for (ExceptionHandler handler : handlers) {
      handler.handle(exception);
    }
  }

  private void attachForThreadUncaughtExceptionHandling() {
    Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
      @Override
      public void uncaughtException(Thread t, Throwable e) {
        handle(e);
      }
    });
  }
}