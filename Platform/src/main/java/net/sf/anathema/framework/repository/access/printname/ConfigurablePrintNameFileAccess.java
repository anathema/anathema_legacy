package net.sf.anathema.framework.repository.access.printname;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.IRepositoryFileResolver;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ConfigurablePrintNameFileAccess implements PrintNameFileAccess {
  private static final Logger logger = Logger.getLogger(ConfigurablePrintNameFileAccess.class);

  private final IRepositoryFileResolver resolver;
  private final PrintNameFileReader printNameFileReader;

  public ConfigurablePrintNameFileAccess(IRepositoryFileResolver resolver) {
    this.resolver = resolver;
    this.printNameFileReader = new JsonPrintNameFileReader();
  }

  @Override
  public Collection<PrintNameFile> collectAllPrintNameFiles(IItemType type) {
    File repositoryFolder = resolver.getFolder(type.getRepositoryConfiguration());
    File[] subfiles = repositoryFolder.listFiles();
    List<PrintNameFile> printNameFiles = new ArrayList<>();
    if (subfiles == null) {
      return Collections.emptyList();
    }
    for (File subFile : subfiles) {
      PrintNameFile printNameFile = createPrintNameFile(subFile, type);
      if (printNameFile != null) {
        printNameFiles.add(printNameFile);
      }
    }
    return printNameFiles;
  }

  private PrintNameFile createPrintNameFile(File file, IItemType itemType) {
    if (itemType.getRepositoryConfiguration().isItemSavedToSingleFile()) {
      return createSingleFilePrintNameFile(file, itemType);
    }
    return createMultiFilePrintNameFile(file, itemType);
  }

  private PrintNameFile createMultiFilePrintNameFile(File folder, IItemType itemType) {
    if (!folder.isDirectory() || !folder.exists()) {
      return null;
    }
    File mainFile = resolver.getMainFile(folder, itemType.getRepositoryConfiguration());
    if (!mainFile.exists()) {
      return null;
    }
    PrintNameFile mainPrintNameFile = createSingleFilePrintNameFile(mainFile, itemType);
    return new PrintNameFile(mainPrintNameFile.getFile().getParentFile(), mainPrintNameFile.getPrintName(), mainPrintNameFile.getRepositoryId(),
            mainPrintNameFile.getItemType());
  }

  private PrintNameFile createSingleFilePrintNameFile(File file, IItemType itemType) {
    if (file.isDirectory() || !file.exists()) {
      return null;
    }
    try {
      return printNameFileReader.readPrintName(file, itemType);
    } catch (IOException e) {
      logger.error(e);
      return null;
    }
  }

  @Override
  public PrintNameFile getPrintNameFile(IItemType itemType, String repositoryId) {
    for (PrintNameFile printNameFile : collectAllPrintNameFiles(itemType)) {
      if (printNameFile.getRepositoryId().equals(repositoryId)) {
        return printNameFile;
      }
    }
    throw new IllegalStateException("Unknown file requested. Type " + itemType.getId() + ", id " + repositoryId);
  }
}