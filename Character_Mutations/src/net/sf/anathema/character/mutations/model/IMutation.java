package net.sf.anathema.character.mutations.model;

import net.sf.anathema.character.library.quality.presenter.IQuality;

public interface IMutation extends IQuality {

  public void accept(IMutationVisitor visitor);
  
  public int getCost();
}