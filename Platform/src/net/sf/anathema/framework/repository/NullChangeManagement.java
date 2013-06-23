package net.sf.anathema.framework.repository;

import net.sf.anathema.lib.control.IChangeListener;

public class NullChangeManagement implements ChangeManagement {
  @Override
  public boolean isDirty() {
    return false;
  }

  @Override
  public void setClean() {
    //nothing to do
  }

  @Override
  public void removeDirtyListener(IChangeListener changeListener) {
    //nothing to do
  }

  @Override
  public void addDirtyListener(IChangeListener changeListener) {
    //nothing to do
  }
}