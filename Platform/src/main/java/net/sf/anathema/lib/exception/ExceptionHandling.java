package net.sf.anathema.lib.exception;

public class ExceptionHandling {

  private final ExtensibleExceptionHandler handler = new ExtensibleExceptionHandler();

  public ExtensibleExceptionHandler create() {
    attachForThreadUncaughtExceptionHandling();
    handler.addHandler(new ConsoleExceptionHandler());
    handler.addHandler(new LoggingExceptionHandler());
    return handler;
  }

  private void attachForThreadUncaughtExceptionHandling() {
    Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
      @Override
      public void uncaughtException(Thread t, Throwable e) {
        handler.handle(e);
      }
    });
  }
}
