package net.sf.anathema.character.main.model.traits;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.TraitValueStrategy;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.model.traits.TraitIterable;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface TraitModel extends TraitIterable, TraitMap {

  Identifier ID = new SimpleIdentifier("OverallTraitModel");

  void addTraits(Trait... traits);

  TraitValueStrategy getValueStrategy();
}
