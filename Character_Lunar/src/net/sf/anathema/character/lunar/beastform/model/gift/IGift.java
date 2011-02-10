package net.sf.anathema.character.lunar.beastform.model.gift;

import net.sf.anathema.character.library.quality.presenter.IQuality;

public interface IGift extends IQuality {

  public void accept(IGiftVisitor visitor);
  
  public int getCost();
}