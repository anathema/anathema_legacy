package net.sf.anathema.charmentry.model;

import net.sf.anathema.lib.control.IChangeListener;

public interface IReflexiveSpecialsArbitrator {
  boolean isReflexiveSpecialsAvailable();

  public void addModelListener(IChangeListener listener);
}