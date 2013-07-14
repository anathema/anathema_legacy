package net.sf.anathema.hero.spiritual.advance.creation;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.traits.TraitType;

import java.util.HashMap;
import java.util.Map;

public class VirtueBonusCostCalculator {

  private int dotsSpent;
  private final int maxVirtuePoints;
  private final BonusPointCosts costs;
  private final Map<TraitType, IVirtueCost> costsByVirtue = new HashMap<>();
  private final Trait[] virtues;

  public VirtueBonusCostCalculator(Trait[] virtues, int maxVirtuePoints, BonusPointCosts costs) {
    this.virtues = virtues;
    this.maxVirtuePoints = maxVirtuePoints;
    this.costs = costs;
  }

  public void calculateVirtuePoints() {
    clear();
    for (Trait virtue : virtues) {
      int costFactor = costs.getVirtueCosts().getRatingCosts(virtue.getCalculationValue());
      IVirtueCost cost = handleVirtue(virtue, costFactor);
      costsByVirtue.put(virtue.getType(), cost);
    }
  }

  private IVirtueCost handleVirtue(Trait virtue, int costFactor) {
    int maximumFreeVirtueRank = costs.getMaximumFreeVirtueRank();
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