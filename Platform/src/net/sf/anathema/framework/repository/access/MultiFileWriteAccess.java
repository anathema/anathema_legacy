package net.sf.anathema.framework.repository.access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import net.sf.anathema.framework.repository.RepositoryException;

public class MultiFileWriteAccess implements IRepositoryWriteAccess {

  private final File itemFolder;
  private final String mainFileName;
  private final String extension;

  public MultiFileWriteAccess(File itemFolder, String mainFileName, String extension) {
    this.itemFolder = itemFolder;
    this.mainFileName = mainFileName;
    this.extension = extension;
  }

  public OutputStream createMainOutputStream() throws RepositoryException {
    try {
      return new FileOutputStream(new File(itemFolder, mainFileName + extension));
    }
    catch (FileNotFoundException e) {
      throw new RepositoryException(e);
    }
  }

  public OutputStream createSubOutputStream(String streamID) throws RepositoryException {
    try {
      return new FileOutputStream(new File(itemFolder, streamID + extension));
    }
    catch (FileNotFoundException e) {
      throw new RepositoryException(e);
    }
  }
}