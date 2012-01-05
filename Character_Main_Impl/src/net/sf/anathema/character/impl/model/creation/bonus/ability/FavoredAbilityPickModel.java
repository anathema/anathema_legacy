package net.sf.anathema.character.impl.model.creation.bonus.ability;

import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.impl.model.advance.models.AbstractSpendingModel;
import net.sf.anathema.character.library.trait.IFavorableTraitCostCalculator;

public class FavoredAbilityPickModel extends AbstractSpendingModel {

  private final IFavorableTraitCostCalculator abilityCalculator;
  private final ICreationPoints creationPoints;

  public FavoredAbilityPickModel(IFavorableTraitCostCalculator abilityCalculator, ICreationPoints creationPoints) {
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

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}