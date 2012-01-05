package net.sf.anathema.character.impl.model.creation.bonus.magic;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.impl.model.advance.models.AbstractAdditionalSpendingModel;
import net.sf.anathema.character.impl.model.creation.bonus.IAdditionalMagicLearnPointManagement;

public class DefaultCharmModel extends AbstractAdditionalSpendingModel {

  private final MagicCostCalculator magicCalculator;
  private final IAdditionalMagicLearnPointManagement magicAdditionalPools;
  private final ICreationPoints creationPoints;
  private final IAdditionalRules additionalRules;

  public DefaultCharmModel(
      MagicCostCalculator magicCalculator,
      IAdditionalMagicLearnPointManagement magicAdditionalPools,
      ICreationPoints creationPoints,
      IAdditionalRules additionalRules) {
    super("Charms", "General"); //$NON-NLS-1$//$NON-NLS-2$
    this.magicCalculator = magicCalculator;
    this.magicAdditionalPools = magicAdditionalPools;
    this.creationPoints = creationPoints;
    this.additionalRules = additionalRules;
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

  public int getAlotment() {
    return creationPoints.getDefaultCreationCharmCount();
  }

  public boolean isExtensionRequired() {
    return additionalRules != null && additionalRules.getAdditionalMagicLearnPools().length > 0;
  }

  public int getRequiredSize() {
    return 3;
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