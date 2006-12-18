package net.sf.anathema.framework.repository;

import java.io.File;

import net.sf.anathema.framework.item.IItemType;

public class RepositoryFileResolver {

  private final File repositoryFile;

  public RepositoryFileResolver(File repositoryFile) {
    this.repositoryFile = repositoryFile;
  }

  public File getItemTypeFolder(IItemType type) {
    return new File(repositoryFile, type.getRepositoryConfiguration().getFolderName());
  }

  public File getExistingItemTypeFolder(IItemType type) {
    File typeFolder = getItemTypeFolder(type);
    createNonExistentFolder(typeFolder);
    return typeFolder;
  }

  public File getExistingItemFolder(IItem item) {
    File typeFolder = getExistingItemTypeFolder(item.getItemType());
    File itemFolder = new File(typeFolder, item.getRepositoryLocation().getId());
    createNonExistentFolder(itemFolder);
    return itemFolder;
  }

  public File getExistingDataFolder(String folderName) {
    File folder = new File(repositoryFile, folderName);
    createNonExistentFolder(folder);
    return folder;
  }

  private void createNonExistentFolder(File typeFolder) {
    if (!typeFolder.exists()) {
      typeFolder.mkdir();
    }
  }

  public File getItemFile(IItemType type, String id) {
    String extension = type.getRepositoryConfiguration().getFileExtension();
    return new File(getExistingItemTypeFolder(type), id + extension);
  }

  public File getItemFile(IItem item) {
    IItemType type = item.getItemType();
    String id = item.getId();
    return getItemFile(type, id);
  }
}