package net.sf.anathema.framework.reporting;

import net.sf.anathema.lib.exception.AnathemaException;

public class ReportException extends AnathemaException {

  private static final long serialVersionUID = 4158952534818455777L;

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
