package net.sf.anathema.framework.module.preferences;

import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.initialization.repository.IOFileSystemAbstraction;
import net.sf.anathema.initialization.repository.RepositoryFolderCreator;
import net.sf.anathema.lib.message.Message;

import java.io.File;

import static java.text.MessageFormat.format;
import static net.disy.commons.swing.dialog.message.MessageDialogFactory.showMessageDialog;

public class RepositoryFolderWorker {
  public boolean isValid(File folder) {
    try {
      create(folder);
      return true;
    } catch (RepositoryException e) {
      handleException(e, "Invalid directory: ");
      return false;
    }
  }

  public File createFolder(File folder) {
    try {
      create(folder);
      return folder;
    } catch (RepositoryException e) {
      handleException(e, format("Could not create {0}:", folder.getAbsolutePath()));
      return null;
    }
  }

  private void create(File folder) {
    IOFileSystemAbstraction fileSystem = new IOFileSystemAbstraction();
    new RepositoryFolderCreator(fileSystem, new CanonicalPathResolver(folder)).createRepositoryFolder();
  }

  private void handleException(RepositoryException e, String message) {
    Throwable cause = e.getCause();
    if (cause == null) {
      cause = e;
    }
    showMessageDialog(null, new Message(message + cause.getMessage(), cause)); //$NON-NLS-1$
  }
}