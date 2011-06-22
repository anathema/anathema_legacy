package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.lib.exception.AnathemaException;

public class SpellException extends AnathemaException {

  private static final long serialVersionUID = 45583028978108805L;

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