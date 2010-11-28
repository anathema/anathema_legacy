package net.sf.anathema.framework.repository.access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import net.sf.anathema.framework.repository.RepositoryException;

public class SingleFileWriteAccess implements IRepositoryWriteAccess {

  private final File repositoryFile;

  public SingleFileWriteAccess(File repositoryfile) {
    this.repositoryFile = repositoryfile;
  }

  public OutputStream createMainOutputStream() throws RepositoryException {
    try {
      return new FileOutputStream(repositoryFile);
    }
    catch (FileNotFoundException e) {
      throw new RepositoryException(e);
    }
  }

  public OutputStream createSubOutputStream(String streamID) throws RepositoryException {
    throw new UnsupportedOperationException();
  }
}