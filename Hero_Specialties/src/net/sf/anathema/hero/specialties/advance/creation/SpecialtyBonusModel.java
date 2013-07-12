package net.sf.anathema.hero.specialties.advance.creation;

import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.hero.advance.AbstractSpendingModel;

public class SpecialtyBonusModel extends AbstractSpendingModel {
  private final SpecialtiesBonusPointCalculator calculator;
  private final ICreationPoints creationPoints;

  public SpecialtyBonusModel(SpecialtiesBonusPointCalculator calculator, ICreationPoints creationPoints) {
    super("Abilities", "Specialties");
    this.calculator = calculator;
    this.creationPoints = creationPoints;
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
    return creationPoints.getSpecialtyCreationPoints();
  }
}