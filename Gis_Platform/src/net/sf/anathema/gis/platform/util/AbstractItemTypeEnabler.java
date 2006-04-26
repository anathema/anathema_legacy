package net.sf.anathema.gis.platform.util;

import net.sf.anathema.framework.presenter.IItemManagementModelListener;
import net.sf.anathema.framework.repository.IItem;

public abstract class AbstractItemTypeEnabler implements IItemManagementModelListener {

  private final String itemTypeId;

  public AbstractItemTypeEnabler(String itemTypeId) {
    this.itemTypeId = itemTypeId;
  }

  protected final boolean isRelevantItem(IItem item) {
    return item != null && item.getItemType().getId().equals(itemTypeId);
  }
}