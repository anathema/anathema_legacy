package net.sf.anathema.character.model.background;

import net.sf.anathema.character.library.trait.ITrait;

public interface IBackgroundListener {

  public void backgroundAdded(ITrait background);

  public void backgroundRemoved(ITrait background);
}