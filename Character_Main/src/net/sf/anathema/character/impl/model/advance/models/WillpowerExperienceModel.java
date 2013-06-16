package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.library.trait.TraitCollection;
import net.sf.anathema.character.library.trait.TraitCollectionUtilities;

public class WillpowerExperienceModel extends AbstractIntegerValueModel {

  private final TraitCollection traitConfiguration;
  private final IPointCostCalculator calculator;

  public WillpowerExperienceModel(TraitCollection traitConfiguration, IPointCostCalculator calculator) {
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