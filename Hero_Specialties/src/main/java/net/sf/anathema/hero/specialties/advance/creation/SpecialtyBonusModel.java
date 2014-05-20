package net.sf.anathema.hero.specialties.advance.creation;

import net.sf.anathema.hero.advance.overview.model.AbstractSpendingModel;
import net.sf.anathema.hero.specialties.template.SpecialtyPointsTemplate;

public class SpecialtyBonusModel extends AbstractSpendingModel {
  private final SpecialtiesBonusPointCalculator calculator;
  private SpecialtyPointsTemplate costTemplate;

  public SpecialtyBonusModel(SpecialtiesBonusPointCalculator calculator, SpecialtyPointsTemplate costTemplate) {
    super("Abilities", "Specialties");
    this.calculator = calculator;
    this.costTemplate = costTemplate;
  }

  @Override
  public Integer getValue() {
    return calculator.getFreePointsSpent();
  }

  @Override
  public int getSpentBonusPoints() {
    return calculator.getBonusPointCost();
  }

  @Override
  public int getAllotment() {
    return costTemplate.creationPoints;
  }
}