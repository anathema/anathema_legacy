package net.sf.anathema.character.generic.impl.traits.alternate;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;

public interface ITraitRequirement {

  public int getStrictMinimum();

  public int getFreeMinimum();

  public boolean isCurrentlyStrict(IGenericTraitCollection collection);
}