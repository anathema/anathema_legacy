package net.sf.anathema.framework.repository.access;

import net.sf.anathema.framework.repository.RepositoryException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SingleFileReadAccess implements IRepositoryReadAccess {

  private final File repositoryFile;

  public SingleFileReadAccess(File repositoryFile) {
    this.repositoryFile = repositoryFile;
  }

  @Override
  public InputStream openMainInputStream() throws RepositoryException {
    try {
      return new FileInputStream(repositoryFile);
    }
    catch (FileNotFoundException e) {
      throw new RepositoryException(e);
    }
  }

  @Override
  public InputStream openSubInputStream(String streamID) throws RepositoryException {
    throw new UnsupportedOperationException();
  }

  @Override
  public File[] getFiles() {
    return new File[] { repositoryFile };
  }
}