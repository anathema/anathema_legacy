//Copyright (c) 2005 by disy Informationssysteme GmbH
package net.sf.anathema.lib.gui.list.veto;

import net.sf.anathema.lib.exception.AnathemaException;

// NOT_PUBLISHED
public class VetoException extends AnathemaException {

  private static final long serialVersionUID = 6236435238026824921L;

  public VetoException() {
    super();
  }

  public VetoException(String message) {
    super(message);
  }

  public VetoException(String message, Throwable nestedException) {
    super(message, nestedException);
  }

  public VetoException(Throwable nestedException) {
    super(nestedException);
  }
}