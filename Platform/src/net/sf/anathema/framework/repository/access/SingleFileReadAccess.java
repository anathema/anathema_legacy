package net.sf.anathema.framework.repository.access;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import net.sf.anathema.framework.repository.RepositoryException;

public class SingleFileReadAccess implements IRepositoryReadAccess {

  private final File repositoryFile;

  public SingleFileReadAccess(File repositoryFile) {
    this.repositoryFile = repositoryFile;
  }

  public InputStream openMainInputStream() throws RepositoryException {
    try {
      return new FileInputStream(repositoryFile);
    }
    catch (FileNotFoundException e) {
      throw new RepositoryException(e);
    }
  }

  public InputStream openSubInputStream(String streamID) throws RepositoryException {
    throw new UnsupportedOperationException();
  }
}