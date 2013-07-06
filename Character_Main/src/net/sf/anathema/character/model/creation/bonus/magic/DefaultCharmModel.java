package net.sf.anathema.character.model.creation.bonus.magic;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.model.advance.models.AbstractAdditionalSpendingModel;
import net.sf.anathema.character.model.creation.bonus.IAdditionalMagicLearnPointManagement;

public class DefaultCharmModel extends AbstractAdditionalSpendingModel {

  private final MagicCostCalculator magicCalculator;
  private final IAdditionalMagicLearnPointManagement magicAdditionalPools;
  private final ICreationPoints creationPoints;
  private final IAdditionalRules additionalRules;

  public DefaultCharmModel(MagicCostCalculator magicCalculator, IAdditionalMagicLearnPointManagement magicAdditionalPools,
                           ICreationPoints creationPoints, IAdditionalRules additionalRules) {
    super("Charms", "General");
    this.magicCalculator = magicCalculator;
    this.magicAdditionalPools = magicAdditionalPools;
    this.creationPoints = creationPoints;
    this.additionalRules = additionalRules;
  }

  @Override
  public int getAdditionalRestrictedAlotment() {
    return magicAdditionalPools.getAdditionalPointsAmount();
  }

  @Override
  public int getAdditionalValue() {
    return magicCalculator.getAdditionalPointsSpent();
  }

  @Override
  public int getSpentBonusPoints() {
    return getCharmBonusPointsSpent() + getSpellBonusPointsSpent();
  }

  @Override
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

  @Override
  public int getAllotment() {
    return creationPoints.getDefaultCreationCharmCount();
  }

  @Override
  public boolean isExtensionRequired() {
    return additionalRules != null && additionalRules.getAdditionalMagicLearnPools().length > 0;
  }

  @Override
  public int getRequiredSize() {
    return 3;
  }
}