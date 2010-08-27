package net.sf.anathema.gis.platform.util;

import javax.swing.Action;

import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.AnathemaException;

public final class RestrictiveItemTypeEnabler extends AbstractItemTypeEnabler {

  private final Action action;

  public RestrictiveItemTypeEnabler(Action action, String itemTypeId) {
    super(itemTypeId);
    this.action = action;
  }

  @Override
  public void itemRemoved(IItem item) {
    if (isRelevantItem(item)) {
      action.setEnabled(true);
    }
  }

  @Override
  public void itemSelected(IItem item) {
    // nothing to do
  }

  @Override
  public void itemAdded(IItem item) throws AnathemaException {
    if (isRelevantItem(item)) {
      action.setEnabled(false);
    }
  }
}