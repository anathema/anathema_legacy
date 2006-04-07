package net.sf.anathema.character.intimacies.model;

import net.sf.anathema.character.library.trait.ITrait;

public interface IIntimacy {

  public String getName();

  public ITrait getTrait();

  public void resetCurrentValue();
}