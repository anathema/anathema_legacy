package net.sf.anathema.character.model.background;

import net.sf.anathema.character.library.trait.IModifiableTrait;

public interface IBackgroundListener {

  public void backgroundAdded(IModifiableTrait background);

  public void backgroundRemoved(IModifiableTrait background);
}