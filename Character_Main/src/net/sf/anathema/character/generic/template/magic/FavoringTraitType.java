package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.util.Identified;

public interface FavoringTraitType extends Identified {
  ITraitType[] getTraitTypesForGenericCharms();

  ITraitType getSpellFavoringType();
}