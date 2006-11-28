package net.sf.anathema.framework.itemdata.model;

import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.lib.control.change.IChangeListener;

public class BasicItemData implements IBasicItemData {

  private final IItemDescription description = new ItemDescription();

  public void setPrintNameAdjuster(PrintNameAdjuster adjuster) {
    description.getName().addTextChangedListener(adjuster);
  }

  public IItemDescription getDescription() {
    return description;
  }

  public void addDirtyListener(IChangeListener changeListener) {
    // nothing to do
  }

  public boolean isDirty() {
    return true;
  }

  public void setClean() {
    // TODO Auto-generated method stub
  }

  public void removeDirtyListener(IChangeListener changeListener) {
    // nothing to do
  }
}