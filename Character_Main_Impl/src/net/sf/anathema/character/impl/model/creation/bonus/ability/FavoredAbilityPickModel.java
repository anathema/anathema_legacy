package net.sf.anathema.character.impl.model.creation.bonus.ability;

import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.impl.model.advance.models.AbstractSpendingModel;

public class FavoredAbilityPickModel extends AbstractSpendingModel {

  private final AbilityCostCalculator abilityCalculator;
  private final ICreationPoints creationPoints;

  public FavoredAbilityPickModel(AbilityCostCalculator abilityCalculator, ICreationPoints creationPoints) {
    super("Abilities", "FavoredPick"); //$NON-NLS-1$ //$NON-NLS-2$
    this.abilityCalculator = abilityCalculator;
    this.creationPoints = creationPoints;
  }

  public int getSpentBonusPoints() {
    return 0;
  }

  public Integer getValue() {
    return abilityCalculator.getFavoredPicksSpent();
  }

  public int getAlotment() {
    return creationPoints.getAbilityCreationPoints().getFavorableTraitCount();
  }
}