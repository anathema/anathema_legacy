package net.sf.anathema.framework.repository.access;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.framework.repository.RepositoryException;

public class MultiFileReadAccess implements IRepositoryReadAccess {

  private final File itemFolder;
  private final String mainFileName;
  private final String extension;

  public MultiFileReadAccess(File itemFolder, String mainFileName, String extension) {
    Ensure.ensureTrue("Must be a directory.", itemFolder.isDirectory()); //$NON-NLS-1$
    this.itemFolder = itemFolder;
    this.mainFileName = mainFileName;
    this.extension = extension;
  }

  public InputStream openMainInputStream() throws RepositoryException {
    try {
      return openInputStream(mainFileName);
    }
    catch (FileNotFoundException e) {
      throw new RepositoryException(e);
    }
  }

  public InputStream openSubInputStream(String streamID) throws RepositoryException {
    try {
      return openInputStream(streamID);
    }
    catch (FileNotFoundException e) {
      throw new RepositoryException(e);
    }
  }

  private InputStream openInputStream(String id) throws FileNotFoundException {
    return new FileInputStream(new File(itemFolder, id + extension));
  }

  public InputStream openInputStream(File file) throws FileNotFoundException {
    return new FileInputStream(file);
  }

  public File[] getAllFiles() {
    return itemFolder.listFiles();
  }
}