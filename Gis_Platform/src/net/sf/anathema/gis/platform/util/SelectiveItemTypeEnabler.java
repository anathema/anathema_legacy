package net.sf.anathema.gis.platform.util;

import javax.swing.Action;

import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.AnathemaException;

public class SelectiveItemTypeEnabler extends AbstractItemTypeEnabler {

  private final Action action;

  public SelectiveItemTypeEnabler(Action action, String itemTypeId) {
    super(itemTypeId);
    this.action = action;
    action.setEnabled(false);
  }

  public void itemAdded(IItem item) throws AnathemaException {
    // nothing to do
  }

  public void itemSelected(IItem item) {
    action.setEnabled(isRelevantItem(item));
  }

  public void itemRemoved(IItem item) {
    // nothing to do
  }
}