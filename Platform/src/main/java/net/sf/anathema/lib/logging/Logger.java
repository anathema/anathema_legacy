package net.sf.anathema.lib.logging;

public class Logger {

  public synchronized static Logger getLogger(Class<?> logClass) {
    return new Logger(org.slf4j.LoggerFactory.getLogger(logClass));
  }

  private org.slf4j.Logger logger;

  private Logger(org.slf4j.Logger logger) {
    this.logger = logger;
  }

  public void error(String message, Throwable exception) {
    logger.error(message, exception);
  }

  public void error(Throwable throwable) {
    logger.error("", throwable);
  }

  public void info(String message) {
    logger.info(message);
  }

  public void warn(String message) {
    logger.warn(message);
  }

  public void warn(String message, Throwable exception) {
    logger.warn(message, exception);
  }
}