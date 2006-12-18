package net.sf.anathema.framework.repository.access.printname;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.view.PrintNameFile;

public interface IPrintNameFileAccess {

  public PrintNameFile[] collectAllPrintNameFiles(IItemType type);

  public PrintNameFile[] collectClosedPrintNameFiles(IItemType type);

  public PrintNameFile getPrintNameFile(IItemType itemType, String repositoryId);
}