package net.sf.anathema.framework.presenter;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.AnathemaException;

public interface IItemMangementModel {

  public void addItem(IItem item) throws AnathemaException;

  public void setSelectedItem(IItem item);

  public void addListener(IItemManagementModelListener listener);

  public void removeListener(IItemManagementModelListener listener);

  public IItem getSelectedItem();

  public boolean isOpen(String itemId, IItemType type);

  public void removeItem(IItem item);

  public IItem[] getAllItems();
}