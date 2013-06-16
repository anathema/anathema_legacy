package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.main.traits.model.TraitMap;

public interface ITraitCollection extends IGenericTraitCollection, TraitMap {

  @Override
  Trait[] getTraits(ITraitType... traitTypes);
}