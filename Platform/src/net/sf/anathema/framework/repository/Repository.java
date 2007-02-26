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
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class Repository implements IRepository {

  private final PrintNameFileAccess printNameFileAccess;
  private final File repositoryFolder;
  private final File defaultDataFolder;
  private final RepositoryFileResolver resolver;
  private final ChangeControl control = new ChangeControl();

  public Repository(File repositoryFolder, IItemMangementModel itemManagement) {
    Ensure.ensureArgumentTrue("Repositoryfolder must exist.", repositoryFolder.exists()); //$NON-NLS-1$
    this.resolver = new RepositoryFileResolver(repositoryFolder);
    this.defaultDataFolder = resolver.getExistingDataFolder("data"); //$NON-NLS-1$
    this.repositoryFolder = repositoryFolder;
    this.printNameFileAccess = new PrintNameFileAccess(resolver, itemManagement);
  }

  public String getRepositoryPath() {
    return repositoryFolder.toString();
  }

  public IPrintNameFileAccess getPrintNameFileAccess() {
    return printNameFileAccess;
  }

  public IRepositoryFileResolver getRepositoryFileResolver() {
    return resolver;
  }

  public synchronized IRepositoryWriteAccess createWriteAccess(IItem item) throws RepositoryException {
    try {
      if (item.getId() == null) {
        item.getRepositoryLocation().setId(createUniqueRepositoryId(item.getRepositoryLocation()));
      }
      if (item.getItemType().getRepositoryConfiguration().isItemSavedToSingleFile()) {
        return createSingleFileWriteAccess(item);
      }
      return createMultiFileWriteAccess(item);
    }
    catch (RepositoryException e) {
      String pattern = "Could not create RepositoryItem for {0}, {1}."; //$NON-NLS-1$
      throw new RepositoryException(MessageFormat.format(pattern, new Object[] { item.getItemType(), item.getId() }), e);
    }
  }

  public synchronized IRepositoryWriteAccess createWriteAccess(IItemType type, String id) throws RepositoryException {
    try {
      // TODO: Handle non-unique ID
      // if (item.getId() == null) {
      // item.getRepositoryLocation().setId(createUniqueRepositoryId(item.getRepositoryLocation()));
      // }
      if (type.getRepositoryConfiguration().isItemSavedToSingleFile()) {
        return createSingleFileWriteAccess(type, id);
      }
      return createMultiFileWriteAccess(type, id);
    }
    catch (RepositoryException e) {
      String pattern = "Could not create RepositoryItem for {0}, {1}."; //$NON-NLS-1$
      throw new RepositoryException(MessageFormat.format(pattern, new Object[] { type, id }), e);
    }
  }

  private IRepositoryWriteAccess createMultiFileWriteAccess(IItemType type, String id) {
    File itemFolder = resolver.getExistingItemFolder(type, id);
    return createMultiFileWriteAccess(type, itemFolder);
  }

  private IRepositoryWriteAccess createMultiFileWriteAccess(IItem item) {
    File itemFolder = resolver.getExistingItemFolder(item);
    return createMultiFileWriteAccess(item.getItemType(), itemFolder);
  }

  private IRepositoryWriteAccess createMultiFileWriteAccess(IItemType type, File itemFolder) {
    IRepositoryConfiguration configuration = type.getRepositoryConfiguration();
    return new MultiFileWriteAccess(itemFolder, configuration.getMainFileName(), configuration.getFileExtension());
  }

  private IRepositoryWriteAccess createSingleFileWriteAccess(IItemType type, String id) throws RepositoryException {
    File file = resolver.getMainFile(type, id);
    return createSingleFileWriteAccess(file);
  }

  private IRepositoryWriteAccess createSingleFileWriteAccess(IItem item) throws RepositoryException {
    File file = resolver.getItemFile(item);
    return createSingleFileWriteAccess(file);
  }

  private IRepositoryWriteAccess createSingleFileWriteAccess(File file) throws RepositoryException {
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
    return resolver.getMainFile(type, id).exists();
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
    boolean hasClosed = false;
    for (IItemType type : types) {
      hasClosed |= printNameFileAccess.collectClosedPrintNameFiles(type).length > 0;
    }
    return hasClosed;
  }

  public File getDataBaseDirectory(String subfolder) {
    if (subfolder == null) {
      return defaultDataFolder;
    }
    return resolver.getExistingDataFolder(subfolder);
  }

  public void deleteAssociatedItem(PrintNameFile file) throws RepositoryException {
    try {
      FileUtilities.deleteFileOrDirectory(file.getFile());
      refresh();
    }
    catch (IOException e) {
      throw new RepositoryException("Deletion failed.", e); //$NON-NLS-1$
    }
  }

  @Override
  public void addRefreshListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  @Override
  public void refresh() {
    control.fireChangedEvent();
  }
}