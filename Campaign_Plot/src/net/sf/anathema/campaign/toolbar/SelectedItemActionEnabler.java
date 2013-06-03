package net.sf.anathema.campaign.toolbar;

import net.sf.anathema.campaign.item.ItemManagementModelAdapter;
import net.sf.anathema.framework.repository.IItem;

import javax.swing.Action;

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
