package net.sf.anathema.hero.traits;

import net.sf.anathema.character.main.IGenericTraitCollection;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.traits.TraitType;

public class GenericTraitCollectionFacade implements IGenericTraitCollection {

  private TraitMap traitMap;

  public GenericTraitCollectionFacade(TraitMap traitMap) {
    this.traitMap = traitMap;
  }

  @Override
  public ValuedTraitType[] getTraits(TraitType[] traitTypes) {
    return traitMap.getTraits(traitTypes);
  }

  @Override
  public boolean isFavoredOrCasteTrait(TraitType type) {
    return getTrait(type).isCasteOrFavored();
  }

  @Override
  public ValuedTraitType getTrait(TraitType type) {
    return traitMap.getTrait(type);
  }
}
