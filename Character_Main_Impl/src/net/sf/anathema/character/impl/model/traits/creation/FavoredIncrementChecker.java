package net.sf.anathema.character.impl.model.traits.creation;

import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.IFavorableTrait;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class FavoredIncrementChecker implements IIncrementChecker {

  private final int maxFavoredCount;
  private final ICoreTraitConfiguration traitConfiguration;
  private final ITraitType[] traitTypes;

  public static IIncrementChecker createFavoredAbilityIncrementChecker(
      ICharacterTemplate template,
      ICoreTraitConfiguration traitConfiguration) {
    int maxFavoredAbilityCount = template.getCreationPoints().getAbilityCreationPoints().getFavorableTraitCount();
    return new FavoredIncrementChecker(maxFavoredAbilityCount, AbilityType.values(), traitConfiguration);
  }

  public FavoredIncrementChecker(
      int maxFavoredCount,
      ITraitType[] traitTypes,
      ICoreTraitConfiguration traitConfiguration) {
    this.maxFavoredCount = maxFavoredCount;
    this.traitTypes = traitTypes;
    this.traitConfiguration = traitConfiguration;
  }

  public boolean isValidIncrement(int increment) {
    int count = 0;
    for (IFavorableTrait ability : getAllTraits()) {
      if (ability.getFavorization().isFavored()) {
        count++;
      }
    }
    return count + increment <= maxFavoredCount;
  }

  private IFavorableTrait[] getAllTraits() {
    return traitConfiguration.getFavorableTraits(traitTypes);
  }
}