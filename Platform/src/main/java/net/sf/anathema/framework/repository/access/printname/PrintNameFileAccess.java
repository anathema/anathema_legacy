package net.sf.anathema.framework.repository.access.printname;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.view.PrintNameFile;

import java.util.Collection;

public interface PrintNameFileAccess {

  Collection<PrintNameFile> collectAllPrintNameFiles(IItemType type);

  PrintNameFile getPrintNameFile(IItemType itemType, String repositoryId);
}