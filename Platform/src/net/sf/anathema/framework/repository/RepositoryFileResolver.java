package net.sf.anathema.framework.repository;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.IRepositoryConfiguration;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;

public class RepositoryFileResolver implements IRepositoryFileResolver {

  private final File repositoryFile;

  public RepositoryFileResolver(File repositoryFile) {
    this.repositoryFile = repositoryFile;
  }

  @Override
  public File getItemTypeFolder(IItemType type) {
    return new File(repositoryFile, type.getRepositoryConfiguration().getFolderName());
  }

  public File getExistingItemTypeFolder(IItemType type) {
    File typeFolder = getItemTypeFolder(type);
    createNonExistentFolder(typeFolder);
    return typeFolder;
  }

  private File getItemFolder(IItemType type, String id) {
    File typeFolder = getExistingItemTypeFolder(type);
    return new File(typeFolder, id);
  }

  public File getExistingItemFolder(IItemType type, String id) {
    File itemFolder = getItemFolder(type, id);
    createNonExistentFolder(itemFolder);
    return itemFolder;
  }

  public File getExistingItemFolder(IItem item) {
    return getExistingItemFolder(item.getItemType(), item.getId());
  }

  public File getExistingDataFolder(String folderName) {
    File folder = new File(repositoryFile, folderName);
    createNonExistentFolder(folder);
    return folder;
  }

  private void createNonExistentFolder(File typeFolder) {
    if (!typeFolder.exists()) {
      // mkdir may fail, perhaps the client should be somehow notified
      // of this failure.
      typeFolder.mkdir();
    }
  }

  private File getItemFile(IItemType type, String id) {
    String extension = type.getRepositoryConfiguration().getFileExtension();
    return new File(getExistingItemTypeFolder(type), id + extension);
  }

  public File getItemFile(IItem item) {
    IItemType type = item.getItemType();
    String id = item.getId();
    return getItemFile(type, id);
  }

  @Override
  public File getMainFile(IItemType type, String id) {
    if (type.getRepositoryConfiguration().isItemSavedToSingleFile()) {
      return getItemFile(type, id);
    }
    return getMainFile(getItemFolder(type, id), type);
  }

  @Override
  public File getMainFile(File folder, IItemType type) {
    IRepositoryConfiguration repositoryConfiguration = type.getRepositoryConfiguration();
    return new File(folder, repositoryConfiguration.getMainFileName() + repositoryConfiguration.getFileExtension());
  }

  @Override
  public Collection<File> listAllFiles(IItemType itemType) {
    File folder = getExistingItemTypeFolder(itemType);
    String fileExtension = getExtension(itemType);
    return FileUtils.listFiles(folder, new String[]{fileExtension}, false);
  }

  private String getExtension(IItemType itemType) {
    String fileExtension = itemType.getRepositoryConfiguration().getFileExtension();
    if (fileExtension.startsWith(".")) {
      return fileExtension.substring(1);
    }
    return fileExtension;
  }
}