package net.sf.anathema.character.impl.model.creation.bonus.backgrounds;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.additionalrules.ITraitCostModifier;
import net.sf.anathema.character.generic.template.creation.BackgroundCreationPointCosts;
import net.sf.anathema.character.impl.model.creation.bonus.additional.AdditionalBonusPointPoolManagement;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.model.background.IBackgroundConfiguration;

public class BackgroundBonusPointCostCalculator {

  public static final int MAXIMUM_VALUE_FOR_FREEBIE_POINTS = 3;
  private final IBackgroundConfiguration backgroundConfiguration;
  private final BackgroundCreationPointCosts costs;
  private int dotsSpent;
  private int bonusPointsSpent;
  private final AdditionalBonusPointPoolManagement additionalPools;
  private final int freeBackgroundDots;
  private final IAdditionalRules rules;

  public BackgroundBonusPointCostCalculator(AdditionalBonusPointPoolManagement additionalPools, IBackgroundConfiguration backgroundConfiguration,
                                            BackgroundCreationPointCosts costs, int freeBackgroundDots, IAdditionalRules rules) {
    this.additionalPools = additionalPools;
    this.backgroundConfiguration = backgroundConfiguration;
    this.costs = costs;
    this.freeBackgroundDots = freeBackgroundDots;
    this.rules = rules;
  }

  public void calculateBonusPoints() {
    clear();
    IDefaultTrait[] backgrounds = backgroundConfiguration.getBackgrounds();
    IDefaultTrait[] sortedBackgrounds = additionalPools.sortBackgrounds(backgrounds);
    for (IDefaultTrait background : sortedBackgrounds) {
      handleBackground(background);
    }
  }

  private void handleBackground(IDefaultTrait background) {
    int backgroundValue = background.getCalculationValue();
    ITraitCostModifier costModifier =  rules.getBackgroundCostModifier(background.getType());
    int additionalDotsToSpend = costModifier.getAdditionalDotsToSpend(backgroundValue);
    int additionalBonusPointsToSpend = costModifier.getAdditionalBonusPointsToSpend(backgroundValue);
    int modifiedTotalBackgroundValue = backgroundValue + additionalDotsToSpend;
    int dotsToSpent = Math.max(0, Math.min(backgroundValue, MAXIMUM_VALUE_FOR_FREEBIE_POINTS) - background.getInitialValue()) + additionalDotsToSpend;
    int remainingDots = freeBackgroundDots - dotsSpent;
    int dotsSpentOnBackground = Math.min(remainingDots, dotsToSpent);
    dotsSpent += dotsSpentOnBackground;
    int bonusPointsSpentOnBackground = 0;
    for (int value = dotsSpentOnBackground + background.getInitialValue(); value < modifiedTotalBackgroundValue; value++) {
      bonusPointsSpentOnBackground += costs.getBackgroundBonusPointCost().getRatingCosts(value);
    }
    bonusPointsSpentOnBackground += additionalBonusPointsToSpend;
    bonusPointsSpent += bonusPointsSpentOnBackground;
    additionalPools.spendOn(background, bonusPointsSpentOnBackground);
  }

  private void clear() {
    dotsSpent = 0;
    bonusPointsSpent = 0;
  }

  public int getSpentDots() {
    return dotsSpent;
  }

  public int getBonusPointSpent() {
    return bonusPointsSpent;
  }
}