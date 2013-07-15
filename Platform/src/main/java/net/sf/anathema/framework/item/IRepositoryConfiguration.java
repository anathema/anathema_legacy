package net.sf.anathema.framework.item;

public interface IRepositoryConfiguration {

  String getMainFileName();

  boolean isItemSavedToSingleFile();

  String getFileExtension();

  String getFolderName();
}