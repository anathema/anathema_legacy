package net.sf.anathema.lib.model;

import net.sf.anathema.lib.control.ChangeListener;

public interface IChangeableModel {

  void addChangeListener(ChangeListener listener);

  void removeChangeListener(ChangeListener listener);
}