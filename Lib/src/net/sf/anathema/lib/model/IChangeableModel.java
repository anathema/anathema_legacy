package net.sf.anathema.lib.model;

import net.sf.anathema.lib.control.IChangeListener;

public interface IChangeableModel {

  void addChangeListener(IChangeListener listener);

  void removeChangeListener(IChangeListener listener);
}