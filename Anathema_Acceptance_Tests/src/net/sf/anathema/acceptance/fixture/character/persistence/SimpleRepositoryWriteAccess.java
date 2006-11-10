package net.sf.anathema.acceptance.fixture.character.persistence;

import java.io.OutputStream;

import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;

public class SimpleRepositoryWriteAccess implements IRepositoryWriteAccess {

  private final OutputStream outputStream;

  public SimpleRepositoryWriteAccess(OutputStream outputStream) {
    this.outputStream = outputStream;
  }

  public OutputStream createMainOutputStream() throws RepositoryException {
    return outputStream;
  }

  public OutputStream createSubOutputStream(String streamID) throws RepositoryException {
    throw new UnsupportedOperationException("No sub stream supported"); //$NON-NLS-1$
  }
}