package net.sf.anathema.charmentry.model;

import net.sf.anathema.lib.control.IChangeListener;

public interface ISimpleSpecialsArbitrator {
  boolean isSimpleSpecialsAvailable();

  void addModelListener(IChangeListener listener);
}