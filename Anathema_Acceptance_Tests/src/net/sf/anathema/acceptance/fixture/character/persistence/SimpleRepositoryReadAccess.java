package net.sf.anathema.acceptance.fixture.character.persistence;

import java.io.File;
import java.io.InputStream;

import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;

public class SimpleRepositoryReadAccess implements IRepositoryReadAccess {

  private final InputStream mainInputStream;

  public SimpleRepositoryReadAccess(InputStream mainInputStream) {
    this.mainInputStream = mainInputStream;
  }

  public InputStream openMainInputStream() throws RepositoryException {
    return mainInputStream;
  }

  public InputStream openSubInputStream(String streamID) throws RepositoryException {
    throw new UnsupportedOperationException("No sub stream supported"); //$NON-NLS-1$
  }

  public File[] getAllFiles() {
    throw new UnsupportedOperationException("Stream operations only"); //$NON-NLS-1$
  }

  public InputStream openInputStream(File file) {
    throw new UnsupportedOperationException("Stream operations only"); //$NON-NLS-1$
  }
}