package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface ITraitCollection extends IGenericTraitCollection {

  @Override
  ITrait getTrait(ITraitType type);

  @Override
  ITrait[] getTraits(ITraitType[] traitTypes);
}