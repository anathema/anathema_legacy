package net.sf.anathema.charmentry.model;

import net.sf.anathema.lib.control.change.IChangeListener;


public interface ISimpleSpecialsArbitrator {
  public boolean isSimpleSpecialsAvailable();

  public void addModelListener(IChangeListener listener);
}