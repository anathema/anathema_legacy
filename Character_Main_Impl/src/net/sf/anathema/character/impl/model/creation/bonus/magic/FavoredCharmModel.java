package net.sf.anathema.character.impl.model.creation.bonus.magic;

import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.impl.model.advance.models.AbstractSpendingModel;

public class FavoredCharmModel extends AbstractSpendingModel {
  private final MagicCostCalculator magicCalculator;
  private final ICreationPoints creationPoints;

  public FavoredCharmModel(MagicCostCalculator magicCalculator, ICreationPoints creationPoints) {
    super("Charms", "Favored"); //$NON-NLS-1$ //$NON-NLS-2$
    this.magicCalculator = magicCalculator;
    this.creationPoints = creationPoints;
  }

  public int getSpentBonusPoints() {
    return 0;
  }

  public Integer getValue() {
    return magicCalculator.getFavoredCharmPicksSpent();
  }

  public int getAlotment() {
    return creationPoints.getFavoredCreationCharmCount();
  }
}