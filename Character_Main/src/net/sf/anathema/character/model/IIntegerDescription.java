package net.sf.anathema.character.model;

import net.sf.anathema.lib.control.IChangeListener;

public interface IIntegerDescription {

  int getValue();

  void setValue(int value);

  void addChangeListener(IChangeListener listener);
}