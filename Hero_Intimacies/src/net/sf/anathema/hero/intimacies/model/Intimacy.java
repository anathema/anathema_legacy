package net.sf.anathema.hero.intimacies.model;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;

public interface Intimacy {

  String getName();

  Trait getTrait();

  void resetCurrentValue();

  void setComplete(boolean complete);

  boolean isComplete();

  void addCompletionListener(IBooleanValueChangedListener listener);
}