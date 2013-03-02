package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

import java.util.ArrayList;
import java.util.List;

public class AbilityExperienceModel extends AbstractIntegerValueModel {

  private final ICoreTraitConfiguration traitConfiguration;
  private final IPointCostCalculator calculator;
  private final ICharacter character;

  public AbilityExperienceModel(ICoreTraitConfiguration traitConfiguration, IPointCostCalculator calculator, ICharacter character) {
    super("Experience", "Abilities"); //$NON-NLS-1$ //$NON-NLS-2$
    this.traitConfiguration = traitConfiguration;
    this.calculator = calculator;
    this.character = character;
  }

  @Override
  public Integer getValue() {
    return getAbilityCosts();
  }

  private int getAbilityCosts() {
    int experienceCosts = 0;
    for (IFavorableTrait ability : getAllAbilities()) {
      experienceCosts += calculator.getAbilityCosts(ability, ability.getFavorization().isCaste() || ability.getFavorization().isFavored());
    }
    return experienceCosts;
  }

  private IFavorableTrait[] getAllAbilities() {
    List<ITraitType> abilityTypes = new ArrayList<>();
    for (IGroupedTraitType type : character.getCharacterTemplate().getAbilityGroups()) {
      abilityTypes.add(type.getTraitType());
    }
    return traitConfiguration.getFavorableTraits(abilityTypes.toArray(new ITraitType[abilityTypes.size()]));
  }
}