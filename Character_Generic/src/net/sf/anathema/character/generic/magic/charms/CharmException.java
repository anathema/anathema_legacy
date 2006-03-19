package net.sf.anathema.character.generic.magic.charms;

import net.sf.anathema.lib.exception.PersistenceException;

public class CharmException extends PersistenceException {

  public CharmException() {
    super();
  }

  public CharmException(String message) {
    super(message);
  }

  public CharmException(String message, Throwable cause) {
    super(message, cause);
  }

  public CharmException(Throwable cause) {
    super(cause);
  }
}
