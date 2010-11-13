package net.sf.anathema.framework.repository;

import net.sf.anathema.framework.item.IRepositoryConfiguration;

public class RepositoryConfiguration implements IRepositoryConfiguration {

  private final String folder;
  private final String extension;
  private final String mainFileName;

  public RepositoryConfiguration(String extension, String folder) {
    this(extension, folder, null);
  }

  public RepositoryConfiguration(String extension, String folder, String mainFileName) {
    this.extension = extension;
    this.folder = folder;
    this.mainFileName = mainFileName;
  }

  public String getFileExtension() {
    return extension;
  }

  public String getFolderName() {
    return folder;
  }

  public String getMainFileName() {
    return mainFileName;
  }

  public boolean isItemSavedToSingleFile() {
    return mainFileName == null;
  }
}
