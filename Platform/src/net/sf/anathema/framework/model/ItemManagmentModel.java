package net.sf.anathema.framework.model;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.IItemManagementModelListener;
import net.sf.anathema.framework.presenter.IItemManagementModel;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.exception.AnathemaException;

import java.util.ArrayList;
import java.util.List;

public class ItemManagmentModel implements IItemManagementModel {

  private final GenericControl<IItemManagementModelListener> listeners = new GenericControl<IItemManagementModelListener>();
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

  private void fireItemRemovedEvent(final IItem item) {
    listeners.forAllDo(new IClosure<IItemManagementModelListener>() {
      @Override
      public void execute(IItemManagementModelListener input) {
        input.itemRemoved(item);
      }
    });
  }

  private void fireItemAddedEvent(final IItem item) throws AnathemaException {
    try {
      listeners.forAllDo(new IClosure<IItemManagementModelListener>() {
        @Override
        public void execute(IItemManagementModelListener input) {
          try {
            input.itemAdded(item);
          } catch (AnathemaException e) {
            throw new RuntimeException(e);
          }
        }
      });
    } catch (RuntimeException e) {
      if (e.getCause() instanceof AnathemaException) {
        throw (AnathemaException) e.getCause();
      }
      throw e;
    }
  }

  private void fireCharacterSelectionChangedEvent(final IItem item) {
    listeners.forAllDo(new IClosure<IItemManagementModelListener>() {
      @Override
      public void execute(IItemManagementModelListener input) {
        input.itemSelected(item);
      }
    });
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
