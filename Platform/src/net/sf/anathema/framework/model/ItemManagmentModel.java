package net.sf.anathema.framework.model;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.IItemManagementModelListener;
import net.sf.anathema.framework.presenter.IItemMangementModel;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.AnathemaException;

public class ItemManagmentModel implements IItemMangementModel {

  private final List<IItemManagementModelListener> listeners = new ArrayList<IItemManagementModelListener>();
  private final List<IItem> allItems = new ArrayList<IItem>();
  private IItem selectedItem;

  public synchronized void addItem(IItem item) throws AnathemaException {
    allItems.add(item);
    fireItemAddedEvent(item);
    setSelectedItem(item);
  }

  public synchronized void removeItem() {
    IItem item = getSelectedItem();
    if (item == null) {
      return;
    }
    removeItem(item);
  }

  public void removeItem(IItem item) {
    int itemIndex = allItems.indexOf(item);
    allItems.remove(item);
    fireItemRemovedEvent(item);
    itemIndex = Math.min(itemIndex, allItems.size() - 1);
    setSelectedItem(itemIndex < 0 ? null : allItems.get(itemIndex));
  }

  public synchronized void addListener(IItemManagementModelListener listener) {
    listeners.add(listener);
  }

  private synchronized void fireItemRemovedEvent(final IItem item) {
    for (IItemManagementModelListener listener : new ArrayList<IItemManagementModelListener>(listeners)) {
      listener.itemRemoved(item);
    }
  }

  private synchronized void fireItemAddedEvent(final IItem item) throws AnathemaException {
    for (IItemManagementModelListener input : new ArrayList<IItemManagementModelListener>(listeners)) {
      input.itemAdded(item);
    }
  }

  private void fireCharacterSelectionChangedEvent(final IItem item) {
    for (IItemManagementModelListener listener : new ArrayList<IItemManagementModelListener>(listeners)) {
      listener.itemSelected(item);
    }
  }

  public synchronized void removeListener(IItemManagementModelListener listener) {
    listeners.remove(listener);
  }

  public void setSelectedItem(IItem item) {
    if (this.selectedItem == item) {
      return;
    }
    this.selectedItem = item;
    fireCharacterSelectionChangedEvent(selectedItem);
  }

  public IItem getSelectedItem() {
    return selectedItem;
  }

  public boolean isOpen(String itemId, IItemType type) {
    for (IItem item : allItems) {
      if (ObjectUtilities.equals(itemId, item.getId()) && type == item.getItemType()) {
        return true;
      }
    }
    return false;
  }

  public IItem[] getAllItems() {
    return allItems.toArray(new IItem[allItems.size()]);
  }
}