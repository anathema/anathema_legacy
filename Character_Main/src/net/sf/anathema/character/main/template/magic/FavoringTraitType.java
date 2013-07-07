package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.lib.util.Identifier;

public interface FavoringTraitType extends Identifier {
  TraitType[] getTraitTypesForGenericCharms();

  TraitType getSpellFavoringType();
}