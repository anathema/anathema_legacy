package net.sf.anathema.character.main;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.ValuedTraitType;

public interface GenericTraitProvider {

  ValuedTraitType getTrait(TraitType type);
}
