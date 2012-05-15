package net.sf.anathema.framework.repository.access.printname;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.view.PrintNameFile;

public interface IPrintNameFileAccess {

  PrintNameFile[] collectAllPrintNameFiles(IItemType type);

  PrintNameFile[] collectClosedPrintNameFiles(IItemType type);

  PrintNameFile getPrintNameFile(IItemType itemType, String repositoryId);
}