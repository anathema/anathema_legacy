package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.view.PrintNameFile;

public interface IRepositoryTreeModelListener {

  public void printNameFileAdded(PrintNameFile file);

  public void itemTypeAdded(IItemType type);

  public void printNameFileRemoved(PrintNameFile file);
}