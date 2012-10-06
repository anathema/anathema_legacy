package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.lib.exception.AnathemaException;

public class SpellException extends AnathemaException {

  public SpellException() {
    super();
  }

  public SpellException(String message) {
    super(message);
  }

  public SpellException(String message, Throwable cause) {
    super(message, cause);
  }

  public SpellException(Throwable cause) {
    super(cause);
  }
}