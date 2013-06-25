package net.sf.anathema.framework.repository;

import net.sf.anathema.lib.control.ChangeListener;

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
  public void removeDirtyListener(ChangeListener changeListener) {
    //nothing to do
  }

  @Override
  public void addDirtyListener(ChangeListener changeListener) {
    //nothing to do
  }
}