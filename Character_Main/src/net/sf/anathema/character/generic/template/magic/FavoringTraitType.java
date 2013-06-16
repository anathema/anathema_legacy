package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.lib.util.Identifier;

public interface FavoringTraitType extends Identifier {
  TraitType[] getTraitTypesForGenericCharms();

  TraitType getSpellFavoringType();
}