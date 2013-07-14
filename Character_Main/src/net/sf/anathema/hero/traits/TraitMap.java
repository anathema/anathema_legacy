package net.sf.anathema.hero.traits;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.traits.TraitType;

public interface TraitMap {

  Trait getTrait(TraitType traitType);

  Trait[] getTraits(TraitType... traitType);

  Trait[] getAll();
}
