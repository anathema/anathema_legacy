package net.sf.anathema.character.model.background;

import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;

public interface IBackgroundListener {

  public void backgroundAdded(IDefaultTrait background);

  public void backgroundRemoved(IDefaultTrait background);
}