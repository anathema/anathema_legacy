package net.sf.anathema.character.main.advance.models;

import net.sf.anathema.character.main.advance.PointCostCalculator;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.template.abilities.GroupedTraitType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.TraitMap;

import java.util.ArrayList;
import java.util.List;

public class AbilityExperienceModel extends AbstractIntegerValueModel {

  private final TraitMap traitMap;
  private final PointCostCalculator calculator;
  private final Hero hero;

  public AbilityExperienceModel(TraitMap traitMap, PointCostCalculator calculator, Hero hero) {
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