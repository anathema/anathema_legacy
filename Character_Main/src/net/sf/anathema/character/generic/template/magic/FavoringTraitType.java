package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.util.Identifier;

public interface FavoringTraitType extends Identifier {
  ITraitType[] getTraitTypesForGenericCharms();

  ITraitType getSpellFavoringType();
}