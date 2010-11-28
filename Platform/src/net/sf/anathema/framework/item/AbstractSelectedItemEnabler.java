package net.sf.anathema.framework.item;

import javax.swing.Action;

import net.sf.anathema.framework.presenter.IItemManagementModelListener;
import net.sf.anathema.framework.presenter.IItemMangementModel;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.AnathemaException;

public abstract class AbstractSelectedItemEnabler {

  private final Action action;

  public AbstractSelectedItemEnabler(final IItemMangementModel model, Action action) {
    this.action = action;
    model.addListener(new IItemManagementModelListener() {
      public void itemAdded(IItem item) throws AnathemaException {
        adjustEnabled(model.getSelectedItem());
      }

      public void itemSelected(IItem item) {
        adjustEnabled(model.getSelectedItem());
      }

      public void itemRemoved(IItem item) {
        adjustEnabled(model.getSelectedItem());
      }
    });
    action.setEnabled(isEnabled(model.getSelectedItem()));
  }

  private void adjustEnabled(IItem selectedItem) {
    action.setEnabled(isEnabled(selectedItem));
  }

  protected abstract boolean isEnabled(IItem selectedItem);
}