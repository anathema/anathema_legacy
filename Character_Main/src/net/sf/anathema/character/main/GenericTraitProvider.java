package net.sf.anathema.character.main;

import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.traits.TraitType;

public interface GenericTraitProvider {

  ValuedTraitType getTrait(TraitType type);
}
