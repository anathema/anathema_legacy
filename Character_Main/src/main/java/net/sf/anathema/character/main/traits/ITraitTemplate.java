package net.sf.anathema.character.main.traits;

import net.sf.anathema.character.main.template.ITraitLimitation;

public interface ITraitTemplate extends ITraitMinimum {

  ITraitLimitation getLimitation();

  LowerableState getLowerableState();

  int getStartValue();

  int getZeroLevelValue();

  boolean isRequiredFavored();
}