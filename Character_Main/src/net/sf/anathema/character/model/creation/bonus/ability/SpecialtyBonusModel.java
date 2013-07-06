package net.sf.anathema.character.model.creation.bonus.ability;

import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.model.advance.models.AbstractSpendingModel;

public class SpecialtyBonusModel extends AbstractSpendingModel {
  private final SpecialtiesCostCalculator calculator;
  private final ICreationPoints creationPoints;

  public SpecialtyBonusModel(SpecialtiesCostCalculator calculator, ICreationPoints creationPoints) {
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