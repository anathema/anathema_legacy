package net.sf.anathema.initialization;

import net.sf.anathema.lib.exception.AnathemaException;

public class InitializationException extends AnathemaException {

  public InitializationException(String message, Throwable cause) {
    super(message, cause);
  }

  public InitializationException(String message) {
    super(message);
  }
}