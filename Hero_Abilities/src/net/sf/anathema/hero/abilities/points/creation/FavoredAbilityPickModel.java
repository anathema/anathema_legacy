package net.sf.anathema.hero.abilities.points.creation;

import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.character.main.advance.models.AbstractSpendingModel;

public class FavoredAbilityPickModel extends AbstractSpendingModel {

  private final IAbilityCostCalculator abilityCalculator;
  private final ICreationPoints creationPoints;

  public FavoredAbilityPickModel(IAbilityCostCalculator abilityCalculator, ICreationPoints creationPoints) {
    super("Abilities", "FavoredPick");
    this.abilityCalculator = abilityCalculator;
    this.creationPoints = creationPoints;
  }

  @Override
  public int getSpentBonusPoints() {
    return 0;
  }

  @Override
  public Integer getValue() {
    return abilityCalculator.getFavoredPicksSpent();
  }

  @Override
  public int getAllotment() {
    return creationPoints.getAbilityCreationPoints().getFavorableTraitCount();
  }
}