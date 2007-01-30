package net.sf.anathema.character.impl.model.creation.bonus.ability;

import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.impl.model.advance.models.AbstractSpendingModel;
import net.sf.anathema.character.library.trait.IFavorableTraitCostCalculator;

public class FavoredAbilityBonusModel extends AbstractSpendingModel {
  private final IFavorableTraitCostCalculator abilityCalculator;
  private final ICreationPoints creationPoints;

  public FavoredAbilityBonusModel(IFavorableTraitCostCalculator abilityCalculator, ICreationPoints creationPoints) {
    super("Abilities", "FavoredDot"); //$NON-NLS-1$ //$NON-NLS-2$
    this.abilityCalculator = abilityCalculator;
    this.creationPoints = creationPoints;
  }

  public Integer getValue() {
    return abilityCalculator.getFreePointsSpent(true);
  }

  public int getSpentBonusPoints() {
    return 0;
  }

  public int getAlotment() {
    return creationPoints.getAbilityCreationPoints().getFavoredDotCount();
  }
}