package net.sf.anathema.initialization;

import net.sf.anathema.lib.exception.AnathemaException;

public class InitializationException extends AnathemaException {

  private static final long serialVersionUID = 7565185295375434953L;

  public InitializationException(String message, Throwable cause) {
    super(message, cause);
  }
}