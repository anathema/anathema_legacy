package net.sf.anathema.lib.logging;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import net.disy.commons.core.util.StringUtilities;

public class Logger {

  private static final String DEBUG_PREFIX = "[DEBUG] "; //$NON-NLS-1$
  private static final String ERROR_PREFIX = "[ERROR] "; //$NON-NLS-1$
  private static final String INFO_PREFIX = "[INFO] "; //$NON-NLS-1$
  private static final Map<Class, Logger> loggers = new HashMap<Class, Logger>();
  private static final String WARN_PREFIX = "[WARN] "; //$NON-NLS-1$

  public synchronized static Logger getLogger(Class logClass) {
    if (loggers.containsKey(logClass)) {
      return loggers.get(logClass);
    }
    Logger logger = new Logger();
    loggers.put(logClass, logger);
    return logger;
  }

  private PrintStream printStream = System.err;

  private Logger() {
    // nothing to do
  }

  public void debug(String message) {
    debug(message, null);
  }

  public void debug(String message, Throwable throwable) {
    if (!isDebug()) {
      return;
    }
    printMessage(DEBUG_PREFIX, message);
    printThrowable(DEBUG_PREFIX, throwable);
  }

  public void debug(Throwable throwable) {
    debug(null, throwable);
  }

  public void error(String message) {
    error(message, null);
  }

  public void error(String message, Throwable exception) {
    if (!isError()) {
      return;
    }
    printMessage(ERROR_PREFIX, message);
    printThrowable(ERROR_PREFIX, exception);
  }

  public void error(Throwable throwable) {
    error(null, throwable);
  }

  public void info(String message) {
    if (!isInfo()) {
      return;
    }
    printMessage(INFO_PREFIX, message);
  }

  private boolean isDebug() {
    return true;
  }

  private boolean isError() {
    return true;
  }

  private boolean isInfo() {
    return true;
  }

  private boolean isWarn() {
    return true;
  }

  private void printMessage(String prefix, String message) {
    if (!StringUtilities.isNullOrEmpty(message)) {
      printStream.println(prefix + message);
    }
  }

  private void printThrowable(String prefix, Throwable throwable) {
    if (throwable == null) {
      return;
    }
    printStream.print(prefix);
    throwable.printStackTrace(printStream);
  }

  public void warn(String message) {
    if (!isWarn()) {
      return;
    }
    printMessage(WARN_PREFIX, message);
  }

  public void warn(String message, Throwable exception) {
    if (!isWarn()) {
      return;
    }
    printMessage(WARN_PREFIX, message);
    printThrowable(WARN_PREFIX, exception);
  }
}