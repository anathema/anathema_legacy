package net.sf.anathema.lib.exception;

import java.util.ArrayList;
import java.util.List;

public class ExtensibleExceptionHandler implements ExceptionHandler {

  private List<ExceptionHandler> handlers = new ArrayList<>();

  public void addHandler(ExceptionHandler handler) {
    handlers.add(handler);
  }

  @Override
  public void handle(Throwable exception) {
    for (ExceptionHandler handler : handlers) {
      handler.handle(exception);
    }
  }
}