package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.repository.access.IRepositoryFileAccess;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IRepositoryTreeModel extends ExportModel {
  public IItemType[] getAllItemTypes();

  public IItemType getItemTypeForId(String id);

  public PrintNameFile[] getPrintNameFiles(IItemType itemType);

  public void addRepositoryTreeModelListener(IRepositoryTreeModelListener listener);

  public void deleteSelection() throws RepositoryException;

  public boolean canSelectionBeDeleted();

  public String getRepositoryPath();

  public void setSelectedObject(Object[] object);

  public void addTreeSelectionChangeListener(IChangeListener changeListener);

  public String createUniqueId(IItemType type, String id);

  public IRepositoryWriteAccess getWriteAccess(IItemType type, String id) throws RepositoryException;

  public String getMainFilePath(IItemType type, String id);

  public void refreshItem(IItemType type, String id);
}