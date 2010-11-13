package net.sf.anathema.framework.presenter;

import javax.swing.Action;

import net.sf.anathema.framework.repository.IItem;

public class SelectedItemActionEnabler extends ItemManagementModelAdapter {

  private final Action action;

  public SelectedItemActionEnabler(Action action, IItem selectedItem) {
    this.action = action;
    adjustEnabled(selectedItem);
  }

  private void adjustEnabled(IItem selectedItem) {
    action.setEnabled(selectedItem != null);
  }

  @Override
  public void itemSelected(IItem item) {
    adjustEnabled(item);
  }
}
