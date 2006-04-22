package net.sf.anathema.charmentry.model;

import net.sf.anathema.lib.control.change.IChangeListener;

public interface IReflexiveCharmModel {

  public void addChangeListener(IChangeListener listener);

  public int getStep();

  public int getDefenseStep();

  public boolean isSplitEnabled();

  public void setSplitEnabled(boolean splitEnabled);

  public void setStep(int newValue);

  public void setDefenseStep(int newValue);

  public void reset();

}
