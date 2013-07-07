package net.sf.anathema.character.main;

import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.traits.TraitType;

public interface IGenericTraitCollection extends GenericTraitProvider {

  ValuedTraitType[] getTraits(TraitType[] traitTypes);

  boolean isFavoredOrCasteTrait(TraitType type);
}
