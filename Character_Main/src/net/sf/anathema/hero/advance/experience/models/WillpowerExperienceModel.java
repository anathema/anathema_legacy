package net.sf.anathema.hero.advance.experience.models;

import net.sf.anathema.hero.advance.experience.PointCostCalculator;
import net.sf.anathema.character.main.library.trait.TraitCollectionUtilities;
import net.sf.anathema.hero.traits.TraitMap;

public class WillpowerExperienceModel extends AbstractIntegerValueModel {

  private final TraitMap traitConfiguration;
  private final PointCostCalculator calculator;

  public WillpowerExperienceModel(TraitMap traitConfiguration, PointCostCalculator calculator) {
    super("Experience", "Willpower");
    this.traitConfiguration = traitConfiguration;
    this.calculator = calculator;
  }

  @Override
  public Integer getValue() {
    return getWillpowerCosts();
  }

  private int getWillpowerCosts() {
    return calculator.getWillpowerCosts(TraitCollectionUtilities.getWillpower(traitConfiguration));
  }
}