package net.sf.anathema.lib.exception;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * Catches all {@link java.lang.Throwable} objects invoked in AWT event dispatch threads or in
 * Threads. The {@link java.lang.Throwable} objects will be delegated to an attached {@link
 * IExceptionHandler} object, see {@link
 * #setHandler(IExceptionHandler)}.
 */
public class CentralExceptionHandling {

  private static final CentralExceptionHandling instance = new CentralExceptionHandling();

  private IExceptionHandler handler;

  private CentralExceptionHandling() {
    attachForEventDispatchExceptionHandling();
    attachForThreadUncaughtExceptionHandling();
  }

  private void attachForThreadUncaughtExceptionHandling() {
    Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
      @Override
      public void uncaughtException(Thread t, Throwable e) {
        handle(e);
      }
    });
  }

  public static void setHandler(IExceptionHandler handler) {
    getInstance().handler = handler;
  }

  public static CentralExceptionHandling getInstance() {
    return instance;
  }

  public void handle(Throwable exception) {
    if (handler != null) {
      handler.handle(exception);
    }
    else {
      System.err.println("Exception occurred during event dispatching:");
      exception.printStackTrace();
    }
  }

  private void attachForEventDispatchExceptionHandling() {
    System.setProperty("sun.awt.exception.handler", InternalAwtExceptionHandler.class.getName());
  }
}