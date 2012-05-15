package net.sf.anathema.lib.model;

import net.sf.anathema.lib.control.IChangeListener;

public interface IChangeableModel {

  public void addChangeListener(IChangeListener listener);

  public void removeChangeListener(IChangeListener listener);
}