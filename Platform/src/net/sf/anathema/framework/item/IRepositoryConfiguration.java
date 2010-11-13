package net.sf.anathema.framework.item;

public interface IRepositoryConfiguration {

  public String getMainFileName();

  public boolean isItemSavedToSingleFile();

  public String getFileExtension();

  public String getFolderName();
}