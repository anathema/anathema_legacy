package net.sf.anathema.lib.exception;

public class PersistenceException extends AnathemaException {

  private static final long serialVersionUID = -2080027597535577602L;

  public PersistenceException() {
    super();
  }

  public PersistenceException(String message) {
    super(message);
  }

  public PersistenceException(String message, Throwable cause) {
    super(message, cause);
  }

  public PersistenceException(Throwable cause) {
    super(cause);
  }
}