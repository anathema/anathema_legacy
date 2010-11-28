package net.sf.anathema.framework.itemdata.model;

import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.lib.control.change.IChangeListener;

public abstract class NonPersistableItemData implements IItemData {

  public void addDirtyListener(IChangeListener changeListener) {
    // nothing to do;
  }

  public boolean isDirty() {
    return false;
  }

  public void removeDirtyListener(IChangeListener changeListener) {
    // nothing to do;
  }

  public void setClean() {
    // nothing to do;
  }

  public void setPrintNameAdjuster(PrintNameAdjuster adjuster) {
    // nothing to do;
  }
}