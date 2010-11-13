package net.sf.anathema.character.impl.model.creation.bonus.magic;

import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.impl.model.advance.models.AbstractSpendingModel;

public class SimpleDefaultCharmModel extends AbstractSpendingModel {

  private final MagicCostCalculator magicCalculator;
  private final ICreationPoints creationPoints;

  public SimpleDefaultCharmModel(MagicCostCalculator magicCalculator, ICreationPoints creationPoints) {
    super("Charms", "General"); //$NON-NLS-1$ //$NON-NLS-2$
    this.magicCalculator = magicCalculator;
    this.creationPoints = creationPoints;
  }

  public int getAlotment() {
    return creationPoints.getDefaultCreationCharmCount();
  }

  public int getSpentBonusPoints() {
    return getCharmBonusPointsSpent() + getSpellBonusPointsSpent();
  }

  public Integer getValue() {
    return magicCalculator.getGeneralCharmPicksSpent();
  }

  private int getCharmBonusPointsSpent() {
    if (magicCalculator == null) {
      return 0;
    }
    return magicCalculator.getBonusPointsSpentForCharms();
  }

  private int getSpellBonusPointsSpent() {
    if (magicCalculator == null) {
      return 0;
    }
    return magicCalculator.getBonusPointsSpentForSpells();
  }
}