package net.sf.anathema.framework.repository.access.printname;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.IRepositoryFileResolver;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class XmlPrintNameFileAccess implements PrintNameFileAccess {
  private static final Logger logger = Logger.getLogger(XmlPrintNameFileAccess.class);

  public static final String ATTRIBUTE_REPOSITORY_PRINTNAME = "repositoryPrintName";
  public static final String ATTRIBUTE_REPOSITORY_ID = "repositoryId";
  private final IRepositoryFileResolver resolver;

  public XmlPrintNameFileAccess(IRepositoryFileResolver resolver) {
    this.resolver = resolver;
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
      return readPrintName(file, itemType);
    } catch (IOException e) {
      logger.error(e);
      return null;
    }
  }

  private PrintNameFile readPrintName(File file, IItemType itemType) throws FileNotFoundException {
    Document doc;
    try {
      doc = DocumentUtilities.read(file.toPath());
    } catch (AnathemaException e) {
      return null;
    }
    Element root = doc.getRootElement();
    String printName = root.attributeValue(ATTRIBUTE_REPOSITORY_PRINTNAME);
    String idName = root.attributeValue(ATTRIBUTE_REPOSITORY_ID);
    if (printName == null || idName == null) {
      return null;
    }
    return new PrintNameFile(file, printName, idName, itemType);
  }

  @Override
  public PrintNameFile getPrintNameFile(IItemType itemType, String repositoryId) {
    for (PrintNameFile printNameFile : collectAllPrintNameFiles(itemType)) {
      if (printNameFile.getRepositoryId().equals(repositoryId)) {
        return printNameFile;
      }
    }
    return null;
  }
}
