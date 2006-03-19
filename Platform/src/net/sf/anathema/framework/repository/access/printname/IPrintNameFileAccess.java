package net.sf.anathema.framework.repository.access.printname;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.IItemMangementModel;
import net.sf.anathema.framework.view.PrintNameFile;

public interface IPrintNameFileAccess {

  public PrintNameFile[] collectPrintNameFiles(IItemType type);

  public PrintNameFile[] collectPrintNameFiles(IItemType type, IItemMangementModel itemManagement);

  public PrintNameFile getPrintNameFile(IItemType itemType, String repositoryId);
}