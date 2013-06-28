package net.sf.anathema.character.generic.traits;

import net.sf.anathema.character.generic.template.ITraitLimitation;

public interface ITraitTemplate extends ITraitMinimum {

  ITraitLimitation getLimitation();

  LowerableState getLowerableState();

  int getStartValue();

  int getZeroLevelValue();

  boolean isRequiredFavored();
}