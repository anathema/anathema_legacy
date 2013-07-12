package net.sf.anathema.hero.magic.advance.creation;

import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.character.main.advance.models.AbstractSpendingModel;

public class FavoredCharmModel extends AbstractSpendingModel {
  private final MagicCreationCostCalculator magicCalculator;
  private final ICreationPoints creationPoints;

  public FavoredCharmModel(MagicCreationCostCalculator magicCalculator, ICreationPoints creationPoints) {
    super("Charms", "Favored");
    this.magicCalculator = magicCalculator;
    this.creationPoints = creationPoints;
  }

  @Override
  public int getSpentBonusPoints() {
    return 0;
  }

  @Override
  public Integer getValue() {
    return magicCalculator.getFavoredCharmPicksSpent();
  }

  @Override
  public int getAllotment() {
    return creationPoints.getFavoredCreationMagicCount();
  }
}