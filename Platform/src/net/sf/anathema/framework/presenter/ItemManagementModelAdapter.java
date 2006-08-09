package net.sf.anathema.framework.presenter;

import net.sf.anathema.framework.repository.IItem;

public abstract class ItemManagementModelAdapter implements IItemManagementModelListener {

  public void itemAdded(IItem item) {
    // nothing to do
  }

  public void itemSelected(IItem item) {
    // nothing to do
  }

  public void itemRemoved(IItem item) {
    // nothing to do
  }
}