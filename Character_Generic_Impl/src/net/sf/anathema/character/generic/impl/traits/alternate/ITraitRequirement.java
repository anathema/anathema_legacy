package net.sf.anathema.character.generic.impl.traits.alternate;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;

public interface ITraitRequirement {

  int getStrictMinimum();

  int getFreeMinimum();

  boolean isCurrentlyStrict(IGenericTraitCollection collection);
}