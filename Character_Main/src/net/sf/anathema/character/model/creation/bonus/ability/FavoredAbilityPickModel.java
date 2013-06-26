package net.sf.anathema.character.model.creation.bonus.ability;

import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.library.trait.IFavorableTraitCostCalculator;
import net.sf.anathema.character.model.advance.models.AbstractSpendingModel;

public class FavoredAbilityPickModel extends AbstractSpendingModel {

  private final IFavorableTraitCostCalculator abilityCalculator;
  private final ICreationPoints creationPoints;

  public FavoredAbilityPickModel(IFavorableTraitCostCalculator abilityCalculator, ICreationPoints creationPoints) {
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
  public int getAlotment() {
    return creationPoints.getAbilityCreationPoints().getFavorableTraitCount();
  }
}