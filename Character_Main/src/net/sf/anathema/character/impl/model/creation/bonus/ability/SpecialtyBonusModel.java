package net.sf.anathema.character.impl.model.creation.bonus.ability;

import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.impl.model.advance.models.AbstractSpendingModel;

public class SpecialtyBonusModel extends AbstractSpendingModel {
  private final IAbilityCostCalculator abilityCalculator;
  private final ICreationPoints creationPoints;

  public SpecialtyBonusModel(IAbilityCostCalculator abilityCalculator, ICreationPoints creationPoints) {
    super("Abilities", "Specialties"); //$NON-NLS-1$ //$NON-NLS-2$
    this.abilityCalculator = abilityCalculator;
    this.creationPoints = creationPoints;
  }

  @Override
  public Integer getValue() {
    return abilityCalculator.getFreeSpecialtyPointsSpent();
  }

  @Override
  public int getSpentBonusPoints() {
    return abilityCalculator.getSpecialtyBonusPointCosts();
  }

  @Override
  public int getAlotment() {
    return creationPoints.getSpecialtyCreationPoints();
  }
}