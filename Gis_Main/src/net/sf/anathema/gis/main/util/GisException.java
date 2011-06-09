package net.sf.anathema.gis.main.util;

import net.sf.anathema.lib.exception.AnathemaException;

public class GisException extends AnathemaException {

  private static final long serialVersionUID = -7527648228502209207L;

  public GisException() {
    super();
  }

  public GisException(String message) {
    super(message);
  }

  public GisException(String message, Throwable cause) {
    super(message, cause);
  }

  public GisException(Throwable cause) {
    super(cause);
  }
}