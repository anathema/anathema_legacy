package net.sf.anathema.framework.repository;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

import net.disy.commons.core.util.Ensure;
import net.disy.commons.core.util.FileUtilities;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.IRepositoryConfiguration;
import net.sf.anathema.framework.presenter.IItemMangementModel;
import net.sf.anathema.framework.presenter.action.IFileProvider;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.framework.repository.access.MultiFileReadAccess;
import net.sf.anathema.framework.repository.access.MultiFileWriteAccess;
import net.sf.anathema.framework.repository.access.SingleFileReadAccess;
import net.sf.anathema.framework.repository.access.SingleFileWriteAccess;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.framework.repository.access.printname.PrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;

public class Repository implements IRepository {

  private static File createDataFolder(File repositoryFolder, String folderName) {
    File folder = new File(repositoryFolder, folderName);
    if (!folder.exists()) {
      folder.mkdir();
    }
    return folder;
  }

  private final IItemMangementModel itemManagement;
  private final PrintNameFileAccess printNameFileAccess;
  private final File repositoryFolder;
  private final File defaultDataFolder;

  public Repository(File repositoryFolder, IItemMangementModel itemManagement) {
    Ensure.ensureArgumentTrue("Repositoryfolder must exist.", repositoryFolder.exists()); //$NON-NLS-1$
    this.defaultDataFolder = createDataFolder(repositoryFolder, "data"); //$NON-NLS-1$
    this.repositoryFolder = repositoryFolder;
    this.itemManagement = itemManagement;
    this.printNameFileAccess = new PrintNameFileAccess(repositoryFolder);
  }

  public File getRepositoryFolder() {
    return repositoryFolder;
  }

  public IPrintNameFileAccess getPrintNameFileAccess() {
    return printNameFileAccess;
  }

  private File getFile(IItemType type, String itemId) {
    File folder = printNameFileAccess.getRepositoryFolder(type.getRepositoryConfiguration());
    if (!folder.exists()) {
      folder.mkdir();
    }
    return new File(folder, itemId + type.getRepositoryConfiguration().getFileExtension());
  }

  public synchronized IRepositoryWriteAccess createWriteAccess(IItem item) throws RepositoryException {
    Ensure.ensureNotNull("RepositoryItem must not be null.", item); //$NON-NLS-1$
    IItemType type = item.getItemType();
    String repositoryId = item.getId();
    try {
      if (repositoryId == null) {
        item.getRepositoryLocation().setId(createUniqueRepositoryId(item.getRepositoryLocation()));
      }
      IRepositoryConfiguration repositoryConfiguration = type.getRepositoryConfiguration();
      if (repositoryConfiguration.isItemSavedToSingleFile()) {
        return createSingleFileWriteAccess(item, type);
      }
      File typeFolder = printNameFileAccess.getRepositoryFolder(repositoryConfiguration);
      if (!typeFolder.exists()) {
        typeFolder.mkdir();
      }
      File itemFolder = new File(typeFolder, item.getRepositoryLocation().getId());
      if (!itemFolder.exists()) {
        itemFolder.mkdir();
      }
      return new MultiFileWriteAccess(
          itemFolder,
          repositoryConfiguration.getMainFileName(),
          repositoryConfiguration.getFileExtension());
    }
    catch (RepositoryException e) {
      String pattern = "Could not create RepositoryItem for {0}, {1}."; //$NON-NLS-1$
      throw new RepositoryException(MessageFormat.format(pattern, new Object[] { type, item.getId() }), e);
    }
  }

  private IRepositoryWriteAccess createSingleFileWriteAccess(IItem item, IItemType type) throws RepositoryException {
    File file = getFile(type, item.getId());
    if (!file.exists()) {
      try {
        file.createNewFile();
      }
      catch (IOException e) {
        throw new RepositoryException("Error creating file: " + file, e); //$NON-NLS-1$
      }
    }
    return new SingleFileWriteAccess(file);
  }

  public String createUniqueRepositoryId(IBasicRepositoryIdData repositoryLocation) {
    int count = 0;
    String repositoryId = repositoryLocation.getIdProposal();
    IItemType itemType = repositoryLocation.getItemType();
    while (idExists(itemType, repositoryId)) {
      count++;
      repositoryId = repositoryLocation.getIdProposal() + count;
    }
    return repositoryId;
  }

  private boolean idExists(IItemType type, String id) {
    return getFile(type, id).exists();
  }

  public IRepositoryReadAccess openReadAccess(IItemType type, IFileProvider provider) {
    if (provider.getFile() == null) {
      return null;
    }
    if (type.getRepositoryConfiguration().isItemSavedToSingleFile()) {
      return new SingleFileReadAccess(provider.getFile());
    }
    IRepositoryConfiguration repositoryConfiguration = type.getRepositoryConfiguration();
    return new MultiFileReadAccess(
        provider.getFile(),
        repositoryConfiguration.getMainFileName(),
        repositoryConfiguration.getFileExtension());
  }

  public boolean containsClosed(IItemType... types) {
    int closedObjects = 0;
    for (IItemType type : types) {
      closedObjects += printNameFileAccess.collectPrintNameFiles(type, itemManagement).length;
    }
    return closedObjects > 0;
  }

  public File getDataBaseDirectory(String subfolder) {
    if (subfolder == null) {
      return defaultDataFolder;
    }
    return createDataFolder(getRepositoryFolder(), subfolder);
  }

  public void deleteAssociatedItem(PrintNameFile file) throws RepositoryException {
    try {
      FileUtilities.deleteFileOrDirectory(file.getFile());
    }
    catch (IOException e) {
      throw new RepositoryException("Deletion failed.", e); //$NON-NLS-1$
    }
  }
}