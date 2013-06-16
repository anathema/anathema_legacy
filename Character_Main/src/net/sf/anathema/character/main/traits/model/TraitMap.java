package net.sf.anathema.character.main.traits.model;

import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.trait.Trait;

public interface TraitMap {

  Trait getTrait(TraitType traitType);

  Trait[] getTraits(TraitType... traitType);
}
