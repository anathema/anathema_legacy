package net.sf.anathema.framework.repository.access;

import com.google.common.base.Preconditions;
import net.sf.anathema.framework.repository.RepositoryException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MultiFileReadAccess implements IRepositoryReadAccess {

  private final File itemFolder;
  private final String mainFileName;
  private final String extension;

  public MultiFileReadAccess(File itemFolder, String mainFileName, String extension) {
    Preconditions.checkArgument(itemFolder.isDirectory());
    this.itemFolder = itemFolder;
    this.mainFileName = mainFileName;
    this.extension = extension;
  }

  @Override
  public InputStream openMainInputStream() throws RepositoryException {
    try {
      return openInputStream(mainFileName);
    }
    catch (FileNotFoundException e) {
      throw new RepositoryException(e);
    }
  }

  @Override
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

  @Override
  public File[] getFiles() {
    return itemFolder.listFiles();
  }
}