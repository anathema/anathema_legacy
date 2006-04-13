package net.sf.anathema.character.impl.model.creation.bonus.ability;

import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.impl.model.advance.models.AbstractSpendingModel;

public class DefaultAbilityBonusModel extends AbstractSpendingModel {
  private final AbilityCostCalculator abilityCalculator;
  private final ICreationPoints creationPoints;

  public DefaultAbilityBonusModel(AbilityCostCalculator abilityCalculator, ICreationPoints creationPoints) {
    super("Abilities", "General"); //$NON-NLS-1$ //$NON-NLS-2$
    this.abilityCalculator = abilityCalculator;
    this.creationPoints = creationPoints;
  }

  public Integer getValue() {
    return abilityCalculator.getFreePointsSpent(false);
  }

  public int getSpentBonusPoints() {
    return abilityCalculator.getBonusPointsSpent();
  }

  public int getAlotment() {
    return creationPoints.getAbilityCreationPoints().getDefaultDotCount();
  }
}