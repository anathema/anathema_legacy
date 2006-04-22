package net.sf.anathema.charmentry.presenter;

import net.sf.anathema.lib.control.change.IChangeListener;

public interface ISimpleCharmSpecialsModel {

  public void reset();

  public void setDefenseValue(int newValue);

  public void setSpeed(int newValue);

  public void addChangeListener(IChangeListener listener);

  public int getSpeed();

  public int getDefenseValue();

}
