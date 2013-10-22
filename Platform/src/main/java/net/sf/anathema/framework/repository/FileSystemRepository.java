package net.sf.anathema.framework.repository;

import com.google.common.base.Preconditions;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.RepositoryConfiguration;
import net.sf.anathema.framework.presenter.action.IFileProvider;
import net.sf.anathema.framework.repository.access.RepositoryReadAccess;
import net.sf.anathema.framework.repository.access.RepositoryWriteAccess;
import net.sf.anathema.framework.repository.access.MultiFileReadAccess;
import net.sf.anathema.framework.repository.access.MultiFileWriteAccess;
import net.sf.anathema.framework.repository.access.SingleFileReadAccess;
import net.sf.anathema.framework.repository.access.SingleFileWriteAccess;
import net.sf.anathema.framework.repository.access.printname.FileReferenceAccess;
import net.sf.anathema.framework.repository.access.printname.PrintNameFileAccess;
import net.sf.anathema.framework.repository.access.printname.XmlPrintNameFileAccess;
import net.sf.anathema.framework.repository.access.printname.ReferenceAccess;
import net.sf.anathema.framework.repository.access.printname.ReferenceBuilder;
import net.sf.anathema.framework.view.PrintNameFile;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.MessageFormat;

public class FileSystemRepository implements Repository {

  private final PrintNameFileAccess printNameFileAccess;
  private final File repositoryFolder;
  private final RepositoryFileResolver resolver;

  public FileSystemRepository(File repositoryFolder) {
    Preconditions.checkArgument(repositoryFolder.exists());
    this.resolver = new RepositoryFileResolver(repositoryFolder);
    this.repositoryFolder = repositoryFolder;
    this.printNameFileAccess = new XmlPrintNameFileAccess(resolver);
  }

  @Override
  public String getRepositoryPath() {
    return repositoryFolder.toString();
  }

  @Override
  public PrintNameFileAccess getPrintNameFileAccess() {
    return printNameFileAccess;
  }

  @Override
  public <R> ReferenceAccess<R> createReferenceAccess(IItemType type, ReferenceBuilder<R> builder) {
    return new FileReferenceAccess<>(resolver, type, builder);
  }

  @Override
  public IRepositoryFileResolver getRepositoryFileResolver() {
    return resolver;
  }

  @Override
  public synchronized RepositoryWriteAccess createWriteAccess(IItemType type, String id) throws RepositoryException {
    try {
      if (type.getRepositoryConfiguration().isItemSavedToSingleFile()) {
        return createSingleFileWriteAccess(type, id);
      }
      return createMultiFileWriteAccess(type, id);
    } catch (RepositoryException e) {
      String pattern = "Could not create RepositoryItem for {0}, {1}.";
      throw new RepositoryException(MessageFormat.format(pattern, type, id), e);
    }
  }

  private RepositoryWriteAccess createMultiFileWriteAccess(IItemType type, String id) {
    File itemFolder = resolver.getExistingItemFolder(type, id);
    return createMultiFileWriteAccess(type, itemFolder);
  }

  private RepositoryWriteAccess createMultiFileWriteAccess(IItemType type, File itemFolder) {
    RepositoryConfiguration configuration = type.getRepositoryConfiguration();
    return new MultiFileWriteAccess(itemFolder, configuration.getMainFileName(), configuration.getFileExtension());
  }

  private RepositoryWriteAccess createSingleFileWriteAccess(IItemType type, String id) throws RepositoryException {
    File file = resolver.getMainFile(type.getRepositoryConfiguration(), id);
    return createSingleFileWriteAccess(file);
  }

  @SuppressWarnings("ResultOfMethodCallIgnored")
  private RepositoryWriteAccess createSingleFileWriteAccess(File file) throws RepositoryException {
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        throw new RepositoryException("Error creating file: " + file, e);
      }
    }
    return new SingleFileWriteAccess(file);
  }

  @Override
  public String createUniqueRepositoryId(RepositoryIdData repositoryLocation) {
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
    return resolver.getMainFile(type.getRepositoryConfiguration(), id).exists();
  }

  @Override
  public RepositoryReadAccess openReadAccess(IItemType type, IFileProvider provider) {
    if (provider.getFile() == null) {
      return null;
    }
    if (type.getRepositoryConfiguration().isItemSavedToSingleFile()) {
      return new SingleFileReadAccess(provider.getFile());
    }
    RepositoryConfiguration repositoryConfiguration = type.getRepositoryConfiguration();
    return new MultiFileReadAccess(provider.getFile(), repositoryConfiguration.getMainFileName(),
            repositoryConfiguration.getFileExtension());
  }

  @Override
  public RepositoryReadAccess openReadAccess(IItemType type, String id) {
    if (type.getRepositoryConfiguration().isItemSavedToSingleFile()) {
      return new SingleFileReadAccess(getRepositoryFileResolver().getMainFile(type.getRepositoryConfiguration(), id));
    }
    RepositoryConfiguration repositoryConfiguration = type.getRepositoryConfiguration();
    File itemTypeFolder = getRepositoryFileResolver().getFolder(type.getRepositoryConfiguration());
    File itemFolder = new File(itemTypeFolder, id);
    return new MultiFileReadAccess(itemFolder, repositoryConfiguration.getMainFileName(),
            repositoryConfiguration.getFileExtension());
  }

  @Override
  public boolean knowsItem(IItemType type, String id) {
    if (type.getRepositoryConfiguration().isItemSavedToSingleFile()) {
      return getRepositoryFileResolver().getMainFile(type.getRepositoryConfiguration(), id).exists();
    }
    return getRepositoryFileResolver().getFolder(type.getRepositoryConfiguration()).exists();
  }

  @Override
  public Path getDataBaseDirectory(String subfolder) {
    return resolver.getExistingDataFolder(subfolder).toPath();
  }

  @Override
  public void deleteAssociatedItem(PrintNameFile file) throws RepositoryException {
    try {
      if (file.getFile().exists()) {
        FileUtils.forceDelete(file.getFile());
      }
    } catch (IOException e) {
      throw new RepositoryException("Deletion failed.", e);
    }
  }
}