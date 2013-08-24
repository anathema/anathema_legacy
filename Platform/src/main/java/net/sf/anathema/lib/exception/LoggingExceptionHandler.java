package net.sf.anathema.lib.exception;

import net.sf.anathema.lib.logging.Logger;

public class LoggingExceptionHandler implements ExceptionHandler {
  private static final Logger logger = Logger.getLogger(LoggingExceptionHandler.class);

  @Override
  public void handle(Throwable exception) {
    logger.error("Uncaught Exception:", exception);
  }
}