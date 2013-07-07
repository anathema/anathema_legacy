package net.sf.anathema.character.main.advance.models;

import net.sf.anathema.character.main.library.trait.TraitCollectionUtilities;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.character.main.advance.IPointCostCalculator;

public class WillpowerExperienceModel extends AbstractIntegerValueModel {

  private final TraitMap traitConfiguration;
  private final IPointCostCalculator calculator;

  public WillpowerExperienceModel(TraitMap traitConfiguration, IPointCostCalculator calculator) {
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