package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IRepositoryTreeModel {
  public IItemType[] getAllItemTypes();

  public PrintNameFile[] getPrintNameFiles(IItemType itemType);

  public void addRepositoryTreeModelListener(IRepositoryTreeModelListener listener);

  public void deleteItem() throws RepositoryException;

  public boolean canSelectionBeDeleted();

  public String getRepositoryPath();

  public void setSelectedObject(Object object);

  public void addTreeSelectionChangeListener(IChangeListener changeListener);
}