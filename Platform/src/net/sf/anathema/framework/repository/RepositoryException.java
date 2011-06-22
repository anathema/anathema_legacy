package net.sf.anathema.framework.repository;

import net.sf.anathema.lib.exception.AnathemaException;

public class RepositoryException extends AnathemaException {

  private static final long serialVersionUID = -4988611300519359780L;

  public RepositoryException() {
    super();
  }

  public RepositoryException(String message) {
    super(message);
  }

  public RepositoryException(String message, Throwable cause) {
    super(message, cause);
  }

  public RepositoryException(Throwable cause) {
    super(cause);
  }
}