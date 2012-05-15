package net.sf.anathema.character.intimacies.model;

import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;

public interface IIntimacy {

  String getName();

  IDefaultTrait getTrait();

  void resetCurrentValue();

  void setComplete(boolean complete);

  boolean isComplete();

  void addCompletionListener(IBooleanValueChangedListener listener);
}