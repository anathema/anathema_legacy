package net.sf.anathema.hero.abilities.points.creation;

import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.hero.advance.experience.models.AbstractSpendingModel;

public class DefaultAbilityBonusModel extends AbstractSpendingModel {
  private final AbilityCostCalculator abilityCalculator;
  private final ICreationPoints creationPoints;

  public DefaultAbilityBonusModel(AbilityCostCalculator abilityCalculator, ICreationPoints creationPoints) {
    super("Abilities", "General");
    this.abilityCalculator = abilityCalculator;
    this.creationPoints = creationPoints;
  }

  @Override
  public Integer getValue() {
    return abilityCalculator.getFreePointsSpent(false);
  }

  @Override
  public int getSpentBonusPoints() {
    return abilityCalculator.getBonusPointCost();
  }

  @Override
  public int getAllotment() {
    return creationPoints.getAbilityCreationPoints().getDefaultDotCount();
  }
}