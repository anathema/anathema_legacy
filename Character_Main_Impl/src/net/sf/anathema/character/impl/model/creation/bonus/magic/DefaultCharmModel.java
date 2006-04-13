package net.sf.anathema.character.impl.model.creation.bonus.magic;

import net.sf.anathema.character.impl.model.creation.bonus.IAdditionalMagicLearnPointManagement;
import net.sf.anathema.character.presenter.overview.IAdditionalSpendingModel;

public class DefaultCharmModel implements IAdditionalSpendingModel {

  private final MagicCostCalculator magicCalculator;
  private final IAdditionalMagicLearnPointManagement magicAdditionalPools;

  public DefaultCharmModel(MagicCostCalculator magicCalculator, IAdditionalMagicLearnPointManagement magicAdditionalPools) {
    this.magicCalculator = magicCalculator;
    this.magicAdditionalPools = magicAdditionalPools;
  }

  public int getAdditionalRestrictedAlotment() {
    return magicAdditionalPools.getAdditionalPointsAmount();
  }

  public int getAdditionalValue() {
    return magicCalculator.getAdditionalPointsSpent();
  }

  public int getSpentBonusPoints() {
    return getCharmBonusPointsSpent() + getSpellBonusPointsSpent();
  }

  public Integer getValue() {
    return magicCalculator.getGeneralCharmPicksSpent();
  }

  public int getAdditionalUnrestrictedAlotment() {
    return 0;
  }

  public String getId() {
    return "DefaultCharms"; //$NON-NLS-1$
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