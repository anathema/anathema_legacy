package net.sf.anathema.initialization.repository;

import java.io.File;
import java.io.IOException;

import net.sf.anathema.framework.repository.RepositoryException;

public class RepositoryFolderCreator {

  private final IStringResolver pathResolver;
  private final IFileSystemAbstraction fileSystem;

  public RepositoryFolderCreator(IFileSystemAbstraction fileSystem, IStringResolver pathResolver) {
    this.fileSystem = fileSystem;
    this.pathResolver = pathResolver;
  }

  public File createRepositoryFolder() throws RepositoryException {
    File file = new File(pathResolver.resolve());
    if (!fileSystem.exists(file)) {
      try {
        fileSystem.createFolder(file);
      }
      catch (IOException e) {
        throw new RepositoryException(e);
      }
    }
    if (!fileSystem.canRead(file) || !fileSystem.canWrite(file)) {
      throw new RepositoryException("Read/Write error on repository at " + file.getAbsolutePath()); //$NON-NLS-1$
    }
    return file;
  }
}