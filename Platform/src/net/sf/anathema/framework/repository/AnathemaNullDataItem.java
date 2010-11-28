package net.sf.anathema.framework.repository;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IIdentificate;

public class AnathemaNullDataItem extends AbstractAnathemaItem {

  public AnathemaNullDataItem(IItemType type, IIdentificate identificate) {
    super(type, identificate);
  }

  public void addDirtyListener(IChangeListener changeListener) {
    // nothing to do
  }

  public IItemData getItemData() {
    return null;
  }

  public boolean isDirty() {
    return false;
  }

  public void removeDirtyListener(IChangeListener changeListener) {
    // nothing to do
  }

  public void setClean() {
    // nothing to do
  }
}