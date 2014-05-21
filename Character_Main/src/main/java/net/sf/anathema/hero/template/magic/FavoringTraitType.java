package net.sf.anathema.hero.template.magic;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.lib.util.Identifier;

public interface FavoringTraitType extends Identifier {
  TraitType[] getTraitTypesForGenericCharms();

  TraitType getSpellFavoringType();
}