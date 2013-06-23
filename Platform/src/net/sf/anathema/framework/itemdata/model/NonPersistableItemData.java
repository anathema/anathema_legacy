package net.sf.anathema.framework.itemdata.model;

import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.lib.control.IChangeListener;

public abstract class NonPersistableItemData implements ItemData {

  @Override
  public void addDirtyListener(IChangeListener changeListener) {
    // nothing to do;
  }

  @Override
  public boolean isDirty() {
    return false;
  }

  @Override
  public void removeDirtyListener(IChangeListener changeListener) {
    // nothing to do;
  }

  @Override
  public void setClean() {
    // nothing to do;
  }

  @Override
  public void setPrintNameAdjuster(PrintNameAdjuster adjuster) {
    // nothing to do;
  }
}