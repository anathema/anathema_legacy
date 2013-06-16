package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.main.traits.model.TraitMap;

public interface TraitCollection extends IGenericTraitCollection, TraitMap {

  @Override
  Trait[] getTraits(TraitType... traitTypes);
}