package net.sf.anathema.framework.repository;

import net.sf.anathema.lib.control.ChangeListener;

public interface ChangeManagement {
  boolean isDirty();

  void setClean();

  @SuppressWarnings("UnusedDeclaration")
  void removeDirtyListener(ChangeListener changeListener);

  void addDirtyListener(ChangeListener changeListener);
}