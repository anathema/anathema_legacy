package net.sf.anathema.hero.traits;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.traits.TraitIterable;
import net.sf.anathema.character.main.traits.TraitValueStrategy;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface TraitModel extends TraitIterable, TraitMap {

  Identifier ID = new SimpleIdentifier("OverallTraitModel");

  void addTraits(Trait... traits);

  TraitValueStrategy getValueStrategy();
}
