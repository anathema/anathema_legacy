package net.sf.anathema.character.intimacies.model;

import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;

public interface IIntimacy {

  String getName();

  Trait getTrait();

  void resetCurrentValue();

  void setComplete(boolean complete);

  boolean isComplete();

  void addCompletionListener(IBooleanValueChangedListener listener);
}