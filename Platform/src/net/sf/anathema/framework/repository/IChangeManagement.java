package net.sf.anathema.framework.repository;

import net.sf.anathema.lib.control.change.IChangeListener;

public interface IChangeManagement {
  public boolean isDirty();

  public void setClean();

  public void removeDirtyListener(IChangeListener changeListener);

  public void addDirtyListener(IChangeListener changeListener);
}