package net.sf.anathema.character.main.traits;

import net.sf.anathema.character.main.template.ITraitLimitation;

public interface ITraitTemplate extends ITraitMinimum {

  ITraitLimitation getLimitation();

  ModificationType getModificationType();

  int getStartValue();

  boolean isRequiredFavored();
}