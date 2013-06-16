package net.sf.anathema.character.main.traits.model;

import net.sf.anathema.character.impl.model.traits.TraitIterable;
import net.sf.anathema.character.library.trait.Trait;

public interface TraitModel extends TraitIterable {

  void addTraits(Trait... traits);
}
