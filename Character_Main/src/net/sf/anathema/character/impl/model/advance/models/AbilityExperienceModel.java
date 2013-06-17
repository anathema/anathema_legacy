package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.generic.template.abilities.GroupedTraitType;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.character.model.ICharacter;

import java.util.ArrayList;
import java.util.List;

public class AbilityExperienceModel extends AbstractIntegerValueModel {

  private final TraitMap traitMap;
  private final IPointCostCalculator calculator;
  private final ICharacter character;

  public AbilityExperienceModel(TraitMap traitMap, IPointCostCalculator calculator, ICharacter character) {
    super("Experience", "Abilities");
    this.traitMap =
            traitMap;
    this.calculator = calculator;
    this.character = character;
  }

  @Override
  public Integer getValue() {
    return getAbilityCosts();
  }

  private int getAbilityCosts() {
    int experienceCosts = 0;
    for (Trait ability : getAllAbilities()) {
      experienceCosts += calculator.getAbilityCosts(ability, ability.getFavorization().isCaste() || ability.getFavorization().isFavored());
    }
    return experienceCosts;
  }

  private Trait[] getAllAbilities() {
    List<TraitType> abilityTypes = new ArrayList<>();
    for (GroupedTraitType type : character.getHeroTemplate().getAbilityGroups()) {
      abilityTypes.add(type.getTraitType());
    }
    return traitMap.getTraits(abilityTypes.toArray(new TraitType[abilityTypes.size()]));
  }
}