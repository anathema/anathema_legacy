package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.favorable.IncrementChecker;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.traits.TraitMap;

public class FavoredIncrementChecker implements IncrementChecker {

  private final int maxFavoredCount;
  private final TraitType[] traitTypes;
  private final TraitMap traitMap;

  public FavoredIncrementChecker(int maxFavoredCount, TraitType[] traitTypes, TraitMap traitMap) {
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