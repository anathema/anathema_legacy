package net.sf.anathema.framework.repository;

import net.sf.anathema.framework.item.RepositoryConfiguration;

public class SingleFileConfiguration implements RepositoryConfiguration {

  private final String folder;
  private final String extension;

  public SingleFileConfiguration(String extension, String folder) {
    this.extension = extension;
    this.folder = folder;
  }

  @Override
  public String getFileExtension() {
    return extension;
  }

  @Override
  public String getFolderName() {
    return folder;
  }

  @Override
  public String getMainFileName() {
    return null;
  }

  @Override
  public boolean isItemSavedToSingleFile() {
    return true;
  }
}
