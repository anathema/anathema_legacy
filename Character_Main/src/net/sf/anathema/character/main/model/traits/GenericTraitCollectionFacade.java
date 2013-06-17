package net.sf.anathema.character.main.model.traits;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.generic.traits.TraitType;

public class GenericTraitCollectionFacade implements IGenericTraitCollection {

  private TraitMap traitMap;

  public GenericTraitCollectionFacade(TraitMap traitMap) {
    this.traitMap = traitMap;
  }

  @Override
  public GenericTrait[] getTraits(TraitType[] traitTypes) {
    return traitMap.getTraits(traitTypes);
  }

  @Override
  public boolean isFavoredOrCasteTrait(TraitType type) {
    return getTrait(type).isCasteOrFavored();
  }

  @Override
  public GenericTrait getTrait(TraitType type) {
    return traitMap.getTrait(type);
  }
}
