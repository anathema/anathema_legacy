package net.sf.anathema.framework.itemdata.model;

import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.lib.control.IChangeListener;

public class BasicItemData implements IBasicItemData {

  private final IItemDescription description = new ItemDescription();

  @Override
  public void setPrintNameAdjuster(PrintNameAdjuster adjuster) {
    description.getName().addTextChangedListener(adjuster);
  }

  @Override
  public IItemDescription getDescription() {
    return description;
  }

  @Override
  public boolean isDirty() {
    return description.isDirty();
  }

  @Override
  public void setClean() {
    description.setClean();
  }

  @Override
  public void addDirtyListener(IChangeListener changeListener) {
    description.addDirtyListener(changeListener);
  }

  @Override
  public void removeDirtyListener(IChangeListener changeListener) {
    description.removeDirtyListener(changeListener);
  }
}