package net.sf.anathema.hero.magic.advance.creation;

import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.character.main.advance.models.AbstractSpendingModel;

public class DefaultMagicModel extends AbstractSpendingModel {

  private final MagicBonusPointCalculator magicCalculator;
  private final ICreationPoints creationPoints;

  public DefaultMagicModel(MagicBonusPointCalculator magicCalculator, ICreationPoints creationPoints) {
    super("Charms", "General");
    this.magicCalculator = magicCalculator;
    this.creationPoints = creationPoints;
  }

  @Override
  public int getSpentBonusPoints() {
    if (magicCalculator == null) {
      return 0;
    }
    return magicCalculator.getBonusPointCost();
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