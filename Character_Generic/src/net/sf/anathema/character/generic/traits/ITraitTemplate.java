package net.sf.anathema.character.generic.traits;

import net.sf.anathema.character.generic.template.ITraitLimitation;

public interface ITraitTemplate extends ITraitMinimum {

  public ITraitLimitation getLimitation();

  public LowerableState getLowerableState();

  public int getStartValue();

  public int getZeroLevelValue();

  public boolean isRequiredFavored();
  
  public String getTag();
}