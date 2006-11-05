package net.sf.anathema.framework.itemdata.model;

import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IItemData {

  public void setPrintNameAdjuster(PrintNameAdjuster adjuster);

  public boolean isDirty();

  public void setClean();

  public void addDirtyListener(IChangeListener changeListener);

  public void removeDirtyListener(IChangeListener changeListener);
}