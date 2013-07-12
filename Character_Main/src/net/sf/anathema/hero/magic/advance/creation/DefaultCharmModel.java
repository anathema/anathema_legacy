package net.sf.anathema.hero.magic.advance.creation;

import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.character.main.advance.models.AbstractSpendingModel;
import net.sf.anathema.hero.magic.advance.MagicCostCalculator;

public class DefaultCharmModel extends AbstractSpendingModel {

  private final MagicCostCalculator magicCalculator;
  private final ICreationPoints creationPoints;

  public DefaultCharmModel(MagicCostCalculator magicCalculator, ICreationPoints creationPoints) {
    super("Charms", "General");
    this.magicCalculator = magicCalculator;
    this.creationPoints = creationPoints;
  }

  @Override
  public int getSpentBonusPoints() {
    if (magicCalculator == null) {
      return 0;
    }
    return magicCalculator.getBonusPointsSpent();
  }

  @Override
  public Integer getValue() {
    return magicCalculator.getGeneralCharmPicksSpent();
  }

  @Override
  public int getAllotment() {
    return creationPoints.getDefaultCreationMagicCount();
  }
}