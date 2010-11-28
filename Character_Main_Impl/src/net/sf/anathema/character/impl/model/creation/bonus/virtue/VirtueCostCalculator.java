package net.sf.anathema.character.impl.model.creation.bonus.virtue;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.template.creation.IBonusPointCosts;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;

public class VirtueCostCalculator {

  private int dotsSpent;
  private final int maxVirtuePoints;
  private final IBonusPointCosts costs;
  private final Map<ITraitType, IVirtueCost> costsByVirtue = new HashMap<ITraitType, IVirtueCost>();
  private final IDefaultTrait[] virtues;

  public VirtueCostCalculator(IDefaultTrait[] virtues, int maxVirtuePoints, IBonusPointCosts costs) {
    this.virtues = virtues;
    this.maxVirtuePoints = maxVirtuePoints;
    this.costs = costs;
  }

  public void calculateVirtuePoints() {
    clear();
    for (IDefaultTrait virtue : virtues) {
      int costFactor = costs.getVirtueCosts().getRatingCosts(virtue.getCalculationValue());
      IVirtueCost cost = handleVirtue(virtue, costFactor);
      costsByVirtue.put(virtue.getType(), cost);
    }
  }

  private IVirtueCost handleVirtue(IDefaultTrait virtue, int costFactor) {
    final int maximumFreeVirtueRank = costs.getMaximumFreeVirtueRank();
    int dotsToAdd = Math.min(virtue.getCalculationValue(), maximumFreeVirtueRank) - virtue.getMinimalValue();
    int dotsRemaining = maxVirtuePoints - dotsSpent;
    int dotsAssigned = Math.min(dotsToAdd, dotsRemaining);
    dotsSpent += dotsAssigned;
    int bonusPointsSpent = 0;
    bonusPointsSpent += (dotsToAdd - dotsAssigned) * costFactor;
    bonusPointsSpent += Math.max(virtue.getCreationValue() - maximumFreeVirtueRank, 0) * costFactor;
    return new VirtueCost(dotsAssigned, bonusPointsSpent);
  }

  private void clear() {
    dotsSpent = 0;
    costsByVirtue.clear();
  }

  public int getBonusPointsSpent() {
    int bonusPointsSpent = 0;
    for (IVirtueCost cost : costsByVirtue.values()) {
      bonusPointsSpent += cost.getBonusPointsSpent();
    }
    return bonusPointsSpent;
  }

  public int getVirtueDotsSpent() {
    return dotsSpent;
  }
}