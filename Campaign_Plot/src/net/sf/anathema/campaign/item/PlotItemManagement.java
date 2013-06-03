package net.sf.anathema.campaign.item;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.ItemReceiver;
import net.sf.anathema.framework.repository.IItem;

public interface PlotItemManagement extends ItemReceiver {

  void setSelectedItem(IItem item);

  void addListener(PlotItemManagementListener listener);

  void removeListener(PlotItemManagementListener listener);

  IItem getSelectedItem();

  boolean isOpen(String itemId, IItemType type);

  void removeItem(IItem item);

  IItem[] getAllItems();
}
