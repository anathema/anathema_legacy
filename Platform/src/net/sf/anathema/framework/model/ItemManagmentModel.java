package net.sf.anathema.framework.model;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.IItemManagementModel;
import net.sf.anathema.framework.presenter.IItemManagementModelListener;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.util.ObjectUtilities;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.List;

public class ItemManagmentModel implements IItemManagementModel {

  private final Announcer<IItemManagementModelListener> listeners = Announcer.to(IItemManagementModelListener.class);
  private final List<IItem> allItems = new ArrayList<IItem>();
  private IItem selectedItem;

  @Override
  public synchronized void addItem(IItem item) throws AnathemaException {
    allItems.add(item);
    fireItemAddedEvent(item);
    setSelectedItem(item);
  }

  @Override
  public void removeItem(IItem item) {
    int itemIndex = allItems.indexOf(item);
    allItems.remove(item);
    fireItemRemovedEvent(item);
    itemIndex = Math.min(itemIndex, allItems.size() - 1);
    setSelectedItem(itemIndex < 0 ? null : allItems.get(itemIndex));
  }

  @Override
  public void addListener(IItemManagementModelListener listener) {
    listeners.addListener(listener);
  }

  private void fireItemRemovedEvent(IItem item) {
    listeners.announce().itemRemoved(item);
  }

  private void fireItemAddedEvent(IItem item) throws AnathemaException {
    listeners.announce().itemAdded(item);
  }

  private void fireCharacterSelectionChangedEvent(IItem item) {
    listeners.announce().itemSelected(item);
  }

  @Override
  public void removeListener(IItemManagementModelListener listener) {
    listeners.removeListener(listener);
  }

  @Override
  public void setSelectedItem(IItem item) {
    if (this.selectedItem == item) {
      return;
    }
    this.selectedItem = item;
    fireCharacterSelectionChangedEvent(selectedItem);
  }

  @Override
  public IItem getSelectedItem() {
    return selectedItem;
  }

  @Override
  public boolean isOpen(String itemId, IItemType type) {
    for (IItem item : allItems) {
      if (ObjectUtilities.equals(itemId, item.getId()) && type == item.getItemType()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public IItem[] getAllItems() {
    return allItems.toArray(new IItem[allItems.size()]);
  }
}
