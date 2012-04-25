package net.sf.anathema.framework.presenter;

import net.sf.anathema.framework.repository.IItem;

public abstract class ItemManagementModelAdapter implements IItemManagementModelListener {

  @Override
  public void itemAdded(IItem item) {
    // nothing to do
  }

  @Override
  public void itemSelected(IItem item) {
    // nothing to do
  }

  @Override
  public void itemRemoved(IItem item) {
    // nothing to do
  }
}