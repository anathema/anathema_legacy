package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;

public interface ITraitCollection extends IGenericTraitCollection {

  @Override
  IDefaultTrait getTrait(ITraitType type);

  @Override
  IDefaultTrait[] getTraits(ITraitType[] traitTypes);
}