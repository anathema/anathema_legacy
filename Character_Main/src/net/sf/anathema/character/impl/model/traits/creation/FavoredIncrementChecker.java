package net.sf.anathema.character.impl.model.traits.creation;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.favorable.IncrementChecker;
import net.sf.anathema.character.main.traits.model.TraitMap;

public class FavoredIncrementChecker implements IncrementChecker {

  private final int maxFavoredCount;
  private final ITraitType[] traitTypes;
  private final TraitMap traitMap;

  public FavoredIncrementChecker(int maxFavoredCount, ITraitType[] traitTypes, TraitMap traitMap) {
    this.maxFavoredCount = maxFavoredCount;
    this.traitTypes = traitTypes;
    this.traitMap = traitMap;
  }

  @Override
  public boolean isValidIncrement(int increment) {
    int count = 0;
    for (Trait trait : getAllTraits()) {
      if (trait.getFavorization().isFavored()) {
        count++;
      }
    }
    return count + increment <= maxFavoredCount;
  }

  private Trait[] getAllTraits() {
    return traitMap.getTraits(traitTypes);
  }
}