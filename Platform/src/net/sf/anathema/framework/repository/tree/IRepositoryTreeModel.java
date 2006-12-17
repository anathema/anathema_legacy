package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.view.PrintNameFile;

public interface IRepositoryTreeModel {
  public IItemType[] getAllItemTypes();

  public PrintNameFile[] getPrintNameFiles(IItemType itemType);

  public void addRepositoryTreeModelListener(IRepositoryTreeModelListener listener);

  public void deleteItem(Object currentlySelectedObject) throws RepositoryException;

  public boolean canBeDeleted(Object userObject);
}