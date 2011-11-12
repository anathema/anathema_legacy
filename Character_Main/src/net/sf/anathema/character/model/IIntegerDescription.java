package net.sf.anathema.character.model;

import net.sf.anathema.lib.control.change.IChangeListener;

public interface IIntegerDescription {

  public int getValue();

  public void setValue(int value);

  public void addChangeListener(IChangeListener listener);
}