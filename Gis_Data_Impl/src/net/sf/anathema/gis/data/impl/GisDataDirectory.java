package net.sf.anathema.gis.data.impl;

import java.io.File;
import java.io.IOException;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.gis.data.IGisDataDirectory;
import net.sf.anathema.lib.logging.Logger;

import org.apache.commons.io.FileUtils;

public class GisDataDirectory implements IGisDataDirectory {

  private static final Logger logger = Logger.getLogger(GisDataDirectory.class);
  private File directory;

  @Override
  public boolean canWrite() {
    return exists() && directory.canWrite();
  }

  @Override
  public boolean canRead() {
    return exists() && directory.canRead();
  }

  @Override
  public File getDirectory() {
    return directory;
  }

  private boolean exists() {
    return directory != null && directory.exists();
  }

  public void setDirectory(File directory) {
    String message = "Gisdata directory must be a directory: " + directory.getAbsoluteFile(); //$NON-NLS-1$
    Ensure.ensureArgumentTrue(message, directory.isDirectory());
    this.directory = directory;
    checkDirectory();
  }

  private void checkDirectory() {
    try {
      FileUtils.forceMkdir(directory);
    }
    catch (IOException e) {
      //Nothing to do
    }
    if (!directory.exists()) {
      logger.warn("Gisdata directory does not exist: " + directory.getAbsolutePath()); //$NON-NLS-1$
    }
    else {
      if (!canRead()) {
        logger.warn("Gisdata directory cannot be read: " + directory.getAbsolutePath()); //$NON-NLS-1$
      }
      if (!canWrite())
        logger.warn("Gisdata directory cannot be written: " + directory.getAbsolutePath()); //$NON-NLS-1$
    }
  }
}