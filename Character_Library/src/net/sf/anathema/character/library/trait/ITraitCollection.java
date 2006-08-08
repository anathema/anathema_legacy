package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface ITraitCollection extends IGenericTraitCollection {

  public IModifiableTrait getTrait(ITraitType type);

  public IFavorableModifiableTrait getFavorableTrait(ITraitType type);

  public IModifiableTrait[] getTraits(ITraitType[] traitTypes);

  public IFavorableModifiableTrait[] getFavorableTraits(ITraitType[] traitTypes);
}