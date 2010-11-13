package net.sf.anathema.charmentry.presenter.model;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IDurationEntryModel {

  public void setSimpleDuration(String newValue);

  public void setUntilDuration(String newValue);

  public boolean isDurationComplete();

  public void addModelListener(IChangeListener listener);

  public void clearDuration();

  public void setTraitForAmountDuration(ITraitType newValue);

  public void setValueForAmountDuration(int newValue);

  public void setTextForAmountDuration(String newValue);
}