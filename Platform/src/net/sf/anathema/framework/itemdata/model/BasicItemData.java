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

  public boolean isDirty() {
    return description.isDirty();
  }

  public void setClean() {
    description.setClean();
  }

  public void addDirtyListener(IChangeListener changeListener) {
    description.addDirtyListener(changeListener);
  }

  public void removeDirtyListener(IChangeListener changeListener) {
    description.removeDirtyListener(changeListener);
  }
}