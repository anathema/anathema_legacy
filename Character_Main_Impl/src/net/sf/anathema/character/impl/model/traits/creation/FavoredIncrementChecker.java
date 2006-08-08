package net.sf.anathema.character.impl.model.traits.creation;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.IFavorableModifiableTrait;
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
    List<ITraitType> abilityTypes = new ArrayList<ITraitType>();
    for (IGroupedTraitType traitType : template.getAbilityGroups()) {
      abilityTypes.add(traitType.getTraitType());
    }
    return new FavoredIncrementChecker(
        maxFavoredAbilityCount,
        abilityTypes.toArray(new ITraitType[abilityTypes.size()]),
        traitConfiguration);
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
    for (IFavorableModifiableTrait trait : getAllTraits()) {
      if (trait.getFavorization().isFavored()) {
        count++;
      }
    }
    return count + increment <= maxFavoredCount;
  }

  private IFavorableModifiableTrait[] getAllTraits() {
    return traitConfiguration.getFavorableTraits(traitTypes);
  }
}