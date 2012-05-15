package net.sf.anathema.charmentry.presenter.model;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.control.IChangeListener;

public interface IDurationEntryModel {

  void setSimpleDuration(String newValue);

  void setUntilDuration(String newValue);

  boolean isDurationComplete();

  void addModelListener(IChangeListener listener);

  void clearDuration();

  void setTraitForAmountDuration(ITraitType newValue);

  void setValueForAmountDuration(int newValue);

  void setTextForAmountDuration(String newValue);
}