package net.sf.anathema.framework.presenter;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.IItem;

public interface IItemManagementModel extends ItemReceiver {

  void setSelectedItem(IItem item);

  void addListener(IItemManagementModelListener listener);

  void removeListener(IItemManagementModelListener listener);

  IItem getSelectedItem();

  boolean isOpen(String itemId, IItemType type);

  void removeItem(IItem item);

  IItem[] getAllItems();
}
