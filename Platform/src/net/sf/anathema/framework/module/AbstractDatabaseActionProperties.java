package net.sf.anathema.framework.module;

import java.io.File;
import java.io.IOException;


import org.apache.commons.io.FileUtils;

public abstract class AbstractDatabaseActionProperties implements IDatabaseActionProperties {
  protected final File getParentFolder(File repositoryFolder) throws IOException {
    File directory = new File(repositoryFolder, getFolderName());
    if (!directory.exists()) {
      FileUtils.forceMkdir(directory);
    }
    return directory;
  }

  protected abstract String getFolderName();
}