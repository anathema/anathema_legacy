package net.sf.anathema.hero.charms.advance.creation;

import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.hero.advance.overview.model.AbstractSpendingModel;

public class FavoredMagicModel extends AbstractSpendingModel {
  private final MagicBonusPointCalculator magicCalculator;
  private final ICreationPoints creationPoints;

  public FavoredMagicModel(MagicBonusPointCalculator magicCalculator, ICreationPoints creationPoints) {
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