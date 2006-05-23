package net.sf.anathema.framework;

import net.sf.anathema.lib.exception.AnathemaException;

public class InitializationException extends AnathemaException {

  public InitializationException() {
    super();
  }

  public InitializationException(String message) {
    super(message);
  }

  public InitializationException(String message, Throwable cause) {
    super(message, cause);
  }

  public InitializationException(Throwable cause) {
    super(cause);
  }
}