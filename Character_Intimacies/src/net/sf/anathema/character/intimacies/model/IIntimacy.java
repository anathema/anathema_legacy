package net.sf.anathema.character.intimacies.model;

import net.sf.anathema.character.library.trait.IModifiableTrait;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;

public interface IIntimacy {

  public String getName();

  public IModifiableTrait getTrait();

  public void resetCurrentValue();

  public void setComplete(boolean complete);

  public boolean isComplete();

  public void addCompletionListener(IBooleanValueChangedListener listener);
}