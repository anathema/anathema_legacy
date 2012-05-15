package net.sf.anathema.lib.exception;

/**
 * Common interface for any object able to handle a {@link java.lang.Throwable} object.
 */
public interface IExceptionHandler {
  /**
   * Handles the given {@link Throwable} object. 
   * @param exception the {@link Throwable} object to handle.
   */
  void handle(Throwable exception);
}