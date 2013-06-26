package net.sf.anathema.character.model.advance.models;

import net.sf.anathema.character.generic.template.abilities.GroupedTraitType;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.character.model.advance.IPointCostCalculator;
import net.sf.anathema.hero.model.Hero;

import java.util.ArrayList;
import java.util.List;

public class AbilityExperienceModel extends AbstractIntegerValueModel {

  private final TraitMap traitMap;
  private final IPointCostCalculator calculator;
  private final Hero hero;

  public AbilityExperienceModel(TraitMap traitMap, IPointCostCalculator calculator, Hero hero) {
    super("Experience", "Abilities");
    this.traitMap = traitMap;
    this.calculator = calculator;
    this.hero = hero;
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
    for (GroupedTraitType type : hero.getTemplate().getAbilityGroups()) {
      abilityTypes.add(type.getTraitType());
    }
    return traitMap.getTraits(abilityTypes.toArray(new TraitType[abilityTypes.size()]));
  }
}