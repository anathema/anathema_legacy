package net.sf.anathema.framework.repository;

public class EmptyRepositoryException extends RepositoryException {

  public EmptyRepositoryException() {
    super();
  }

  public EmptyRepositoryException(String message) {
    super(message);
  }

  public EmptyRepositoryException(String message, Throwable cause) {
    super(message, cause);
  }

  public EmptyRepositoryException(Throwable cause) {
    super(cause);
  }
}