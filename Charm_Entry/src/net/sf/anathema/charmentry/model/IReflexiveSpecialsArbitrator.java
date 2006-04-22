package net.sf.anathema.charmentry.model;

import net.sf.anathema.lib.control.change.IChangeListener;

public interface IReflexiveSpecialsArbitrator {

  boolean isReflexiveSpecialsAvailable();

  public void addSpecialsChangeListener(IChangeListener listener);
}