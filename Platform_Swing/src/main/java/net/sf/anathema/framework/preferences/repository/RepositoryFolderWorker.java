package net.sf.anathema.framework.preferences.repository;

import net.sf.anathema.framework.module.preferences.CanonicalPathResolver;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.initialization.repository.IOFileSystemAbstraction;
import net.sf.anathema.initialization.repository.RepositoryFolderCreator;

import java.io.File;

import static java.text.MessageFormat.format;

public class RepositoryFolderWorker {
  public boolean isValid(File folder) {
    create(folder);
    return true;
  }

  public File createFolder(File folder) {
    try {
      create(folder);
      return folder;
    } catch (RepositoryException e) {
      String message = format("Could not create {0}:", folder.getAbsolutePath());
      throw new RepositoryException(message, e);
    }
  }

  private void create(File folder) {
    IOFileSystemAbstraction fileSystem = new IOFileSystemAbstraction();
    new RepositoryFolderCreator(fileSystem, new CanonicalPathResolver(folder)).createRepositoryFolder();
  }
}