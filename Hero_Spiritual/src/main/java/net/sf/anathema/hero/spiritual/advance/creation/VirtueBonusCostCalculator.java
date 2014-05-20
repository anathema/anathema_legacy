package net.sf.anathema.hero.spiritual.advance.creation;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.traits.TraitType;

import java.util.HashMap;
import java.util.Map;

public class VirtueBonusCostCalculator {

  private int dotsSpent;
  private SpiritualCreationData creationData;
  private final Map<TraitType, IVirtueCost> costsByVirtue = new HashMap<>();
  private final Trait[] virtues;

  public VirtueBonusCostCalculator(Trait[] virtues, SpiritualCreationData creationData) {
    this.virtues = virtues;
    this.creationData = creationData;
  }

  public void calculateVirtuePoints() {
    clear();
    for (Trait virtue : virtues) {
      int costFactor = creationData.getVirtueCost().getRatingCosts(virtue.getCalculationValue());
      IVirtueCost cost = handleVirtue(virtue, costFactor);
      costsByVirtue.put(virtue.getType(), cost);
    }
  }

  private IVirtueCost handleVirtue(Trait virtue, int costFactor) {
    int maximumFreeVirtueRank = creationData.getMaximumFreeVirtueRank();
    int dotsToAdd = Math.min(virtue.getCalculationValue(), maximumFreeVirtueRank) - virtue.getMinimalValue();
    int dotsRemaining = creationData.getFreeVirtueCreationDots() - dotsSpent;
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