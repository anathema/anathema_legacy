package net.sf.anathema.framework.repository;

import net.sf.anathema.lib.control.IChangeListener;

public interface ChangeManagement {
  boolean isDirty();

  void setClean();

  void removeDirtyListener(IChangeListener changeListener);

  void addDirtyListener(IChangeListener changeListener);
}