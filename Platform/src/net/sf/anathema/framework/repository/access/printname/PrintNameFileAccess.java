package net.sf.anathema.framework.repository.access.printname;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.IItemMangementModel;
import net.sf.anathema.framework.repository.IRepositoryFileResolver;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.apache.commons.io.FileUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

public class PrintNameFileAccess implements IPrintNameFileAccess {
  // Used for backward compatibility when all fails ...
  public static final String COMPATIBILITY_ENCODING = "ISO-8859-1"; //$NON-NLS-1$
  private static final Logger logger = Logger.getLogger(PrintNameFileAccess.class);
  
  private static final String PRINT_NAME_ATTR = "repositoryPrintName";
  private static final String ID_ATTR = "repositoryId";
  private static final Pattern PRINT_NAME_PATTERN = Pattern.compile(PRINT_NAME_ATTR + "=\"(.*?)\""); //$NON-NLS-1$
  private static final Pattern ID_PATTERN = Pattern.compile(ID_ATTR + "=\"(.*?)\""); //$NON-NLS-1$
  private final IRepositoryFileResolver resolver;
  private final IItemMangementModel itemManagement;

  public PrintNameFileAccess(IRepositoryFileResolver resolver, IItemMangementModel itemManagement) {
    this.resolver = resolver;
    this.itemManagement = itemManagement;
  }

  public PrintNameFile[] collectAllPrintNameFiles(IItemType type) {
    File repositoryFolder = resolver.getItemTypeFolder(type);
    File[] subfiles = repositoryFolder.listFiles();
    List<PrintNameFile> printNameFiles = new ArrayList<PrintNameFile>();
    if (subfiles == null) {
      return new PrintNameFile[0];
    }
    for (File subFile : subfiles) {
      PrintNameFile printNameFile = createPrintNameFile(subFile, type);
      if (printNameFile != null) {
        printNameFiles.add(printNameFile);
      }
    }
    return printNameFiles.toArray(new PrintNameFile[printNameFiles.size()]);
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
    File mainFile = resolver.getMainFile(folder, itemType);
    if (!mainFile.exists()) {
      return null;
    }
    PrintNameFile mainPrintNameFile = createSingleFilePrintNameFile(mainFile, itemType);
    return new PrintNameFile(
        mainPrintNameFile.getFile().getParentFile(),
        mainPrintNameFile.getPrintName(),
        mainPrintNameFile.getRepositoryId(),
        mainPrintNameFile.getItemType());
  }

  private PrintNameFile createSingleFilePrintNameFile(File file, IItemType itemType) {
    if (file.isDirectory() || !file.exists()) {
      return null;
    }
    try {
      try {
        Document doc = DocumentUtilities.read(file);
        Element root = doc.getRootElement();
        Attribute printNameAttr = root.attribute(PRINT_NAME_ATTR);
        Attribute idNameAttr = root.attribute(ID_ATTR);
        
        String printName = printNameAttr != null ? printNameAttr.getValue() : null;
        String idName = idNameAttr != null ? idNameAttr.getValue() : null;
        
        if (printName == null || idName == null) {
          return null;
        }
        
        return new PrintNameFile(file, printName, idName, itemType);
      } catch (AnathemaException ex) {
        // Fall-back to the old method 
        String string = FileUtils.readFileToString(file, COMPATIBILITY_ENCODING);
        Matcher printNameMatcher = PRINT_NAME_PATTERN.matcher(string);
        if (!printNameMatcher.find()) {
          return null;
        }
        Matcher idMatcher = ID_PATTERN.matcher(string);
        if (!idMatcher.find()) {
          return null;
        }
        
        PrintNameFile printNameFile = new PrintNameFile(file, printNameMatcher.group(1), idMatcher.group(1), itemType);
        return printNameFile;
      }
    }
    catch (IOException e) {
      logger.debug(e);
      return null;
    }
  }

  public PrintNameFile[] collectClosedPrintNameFiles(IItemType type) {
    List<PrintNameFile> closedFiles = new ArrayList<PrintNameFile>();
    for (PrintNameFile file : collectAllPrintNameFiles(type)) {
      if (!itemManagement.isOpen(file.getRepositoryId(), type)) {
        closedFiles.add(file);
      }
    }
    return closedFiles.toArray(new PrintNameFile[closedFiles.size()]);
  }

  public PrintNameFile getPrintNameFile(IItemType itemType, String repositoryId) {
    for (PrintNameFile printNameFile : collectAllPrintNameFiles(itemType)) {
      if (printNameFile.getRepositoryId().equals(repositoryId)) {
        return printNameFile;
      }
    }
    return null;
  }
}