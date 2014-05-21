package net.sf.anathema.character.framework.reporting;

import net.sf.anathema.lib.exception.AnathemaException;

public class ReportException extends AnathemaException {

  public ReportException() {
    super();
  }

  public ReportException(String message) {
    super(message);
  }

  public ReportException(String message, Throwable cause) {
    super(message, cause);
  }

  public ReportException(Throwable cause) {
    super(cause);
  }
}
