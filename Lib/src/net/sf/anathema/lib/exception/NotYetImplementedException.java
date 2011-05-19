package net.sf.anathema.lib.exception;

public class NotYetImplementedException extends UnsupportedOperationException {

  private static final long serialVersionUID = 4893428229489419538L;

public NotYetImplementedException() {
    super("Not yet implemented."); //$NON-NLS-1$
  }
}