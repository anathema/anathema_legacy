package net.sf.anathema.character.main.traits.model;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.Trait;

public interface TraitMap {

  Trait getTrait(ITraitType traitType);
}
