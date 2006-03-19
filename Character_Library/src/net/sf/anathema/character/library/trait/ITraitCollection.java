package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface ITraitCollection extends IGenericTraitCollection {

  public ITrait getTrait(ITraitType type);

  public IFavorableTrait getFavorableTrait(ITraitType type);

  public ITrait[] getTraits(ITraitType[] traitTypes);

  public IFavorableTrait[] getFavorableTraits(ITraitType[] traitTypes);
}