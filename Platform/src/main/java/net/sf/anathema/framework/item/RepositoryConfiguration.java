package net.sf.anathema.framework.item;

public interface RepositoryConfiguration {

  String getMainFileName();

  boolean isItemSavedToSingleFile();

  String getFileExtension();

  String getFolderName();
}