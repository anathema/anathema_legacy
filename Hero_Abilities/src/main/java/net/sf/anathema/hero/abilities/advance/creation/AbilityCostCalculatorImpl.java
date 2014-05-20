package net.sf.anathema.hero.abilities.advance.creation;

import net.sf.anathema.character.main.library.ITraitFavorization;
import net.sf.anathema.character.main.library.trait.FavorableTraitCost;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.template.experience.AbilityPointCosts;
import net.sf.anathema.character.main.template.points.IFavorableTraitCreationPoints;
import net.sf.anathema.hero.abilities.model.AbilitiesModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AbilityCostCalculatorImpl implements AbilityCostCalculator {

  private final AbilityPointCosts costs;
  protected final IFavorableTraitCreationPoints points;
  private final Map<Trait, FavorableTraitCost[]> costsByTrait = new HashMap<>();
  private final int freeTraitMax;
  private final Trait[] traits;
  private int favoredPicksSpent = 0;
  private int favoredDotSum = 0;
  private int generalDotSum = 0;

  public AbilityCostCalculatorImpl(AbilitiesModel abilitiesModel, IFavorableTraitCreationPoints points, AbilityPointCosts costs) {
    this.points = points;
    this.freeTraitMax = costs.getMaximumFreeAbilityRank();
    this.traits = abilitiesModel.getAll();
    this.costs = costs;
  }

  protected int getCostFactor(Trait trait) {
    ITraitFavorization favorization = trait.getFavorization();
    return costs.getAbilityCosts(favorization.isCasteOrFavored()).getRatingCosts(trait.getCalculationValue());
  }

  public void recalculate() {
    clear();
    countFavoredTraits();
    Set<Trait> sortedTraits = sortTraitsByStatus();
    for (Trait trait : sortedTraits) {
      int costFactor = getCostFactor(trait);
      FavorableTraitCost[] allCosts;
      if (trait.getFavorization().isCasteOrFavored()) {
        allCosts = handleFavoredTrait(trait, costFactor);
      } else {
        allCosts = handleGeneralTrait(trait, costFactor);
      }
      costsByTrait.put(trait, allCosts);
    }
  }

  private void clear() {
    favoredPicksSpent = 0;
    favoredDotSum = 0;
    generalDotSum = 0;
    costsByTrait.clear();
  }

  private void countFavoredTraits() {
    for (Trait trait : traits) {
      if (trait.getFavorization().isFavored()) {
        increaseFavoredPicksSpent();
      }
    }
  }

  public int getBonusPointCost() {
    int bonusPointSum = 0;
    for (FavorableTraitCost[] allCosts : costsByTrait.values()) {
      for (FavorableTraitCost cost : allCosts) {
        bonusPointSum += cost.getBonusCost();
      }
    }
    return bonusPointSum;
  }

  @Override
  public int getBonusPointsGranted() {
    return 0;
  }

  public FavorableTraitCost[] getCosts(Trait trait) {
    return costsByTrait.get(trait);
  }

  private int getDefaultDotCount() {
    return points.getDefaultDotCount();
  }

  private int getFavoredDotCount() {
    return points.getFavoredDotCount();
  }

  public int getFavoredPicksSpent() {
    return favoredPicksSpent;
  }

  public int getFreePointsSpent(boolean favored) {
    return favored ? favoredDotSum : generalDotSum;
  }

  protected Trait[] getTraits() {
    return traits;
  }

  private FavorableTraitCost handleFavoredSingleTrait(Trait trait, int bonusPointCostFactor) {
    int freeTraitMax = Math.max(this.freeTraitMax, trait.getAbsoluteMinValue());
    int freePointsToAdd = Math.min(trait.getCalculationValue(), freeTraitMax);
    int favoredDotsSpent = 0;
    int generalDotsSpent = 0;
    int bonusPointsSpent = 0;
    if (getFreePointsSpent(true) < getFavoredDotCount()) {
      int remainingFavoredPoints = getFavoredDotCount() - getFreePointsSpent(true);
      favoredDotsSpent = Math.min(remainingFavoredPoints, freePointsToAdd);
      increaseFavoredDotSum(favoredDotsSpent);
      freePointsToAdd -= favoredDotsSpent;
    }
    if (freePointsToAdd > 0) {
      if (getFreePointsSpent(false) < getDefaultDotCount()) {
        int remainingGeneralPoints = getDefaultDotCount() - getFreePointsSpent(false);
        generalDotsSpent = Math.min(remainingGeneralPoints, freePointsToAdd);
        increaseGeneralDotSum(generalDotsSpent);
        freePointsToAdd -= generalDotsSpent;
      }
    }
    if (freePointsToAdd > 0) {
      if (getFreePointsSpent(false) < getDefaultDotCount()) {
        int remainingGeneralPoints = getDefaultDotCount() - getFreePointsSpent(false);
        generalDotsSpent = Math.min(remainingGeneralPoints, freePointsToAdd);
        increaseGeneralDotSum(generalDotsSpent);
        freePointsToAdd -= generalDotsSpent;
      }
    }
    if (freePointsToAdd > 0) {
      bonusPointsSpent += freePointsToAdd * bonusPointCostFactor;
    }
    bonusPointsSpent += Math.max(trait.getCalculationValue() - freeTraitMax, 0) * bonusPointCostFactor;
    return new FavorableTraitCost(bonusPointsSpent, generalDotsSpent, favoredDotsSpent);
  }

  private FavorableTraitCost[] handleFavoredTrait(Trait trait, final int bonusPointCostFactor) {
    final List<FavorableTraitCost> allCosts = new ArrayList<>();
    allCosts.add(handleFavoredSingleTrait(trait, bonusPointCostFactor));
    return allCosts.toArray(new FavorableTraitCost[allCosts.size()]);
  }

  private FavorableTraitCost handleGeneralSingleTrait(Trait trait, int bonusPointCostFactor) {
    int freeTraitMax = Math.max(this.freeTraitMax, trait.getAbsoluteMinValue());
    int freePointsToAdd = Math.min(trait.getCalculationValue(), freeTraitMax);
    int generalDotsSpent = 0;
    int bonusPointsSpent = 0;
    if (getFreePointsSpent(false) < getDefaultDotCount()) {
      int remainingGeneralPoints = getDefaultDotCount() - getFreePointsSpent(false);
      generalDotsSpent = Math.min(remainingGeneralPoints, freePointsToAdd);

      increaseGeneralDotSum(generalDotsSpent);
      freePointsToAdd -= generalDotsSpent;
    }
    if (freePointsToAdd > 0) {
      bonusPointsSpent += freePointsToAdd * bonusPointCostFactor;
    }
    bonusPointsSpent += Math.max(trait.getCalculationValue() - freeTraitMax, 0) * bonusPointCostFactor;
    return new FavorableTraitCost(bonusPointsSpent, generalDotsSpent, 0);
  }

  private FavorableTraitCost[] handleGeneralTrait(Trait trait, final int bonusPointCostFactor) {
    final List<FavorableTraitCost> allCosts = new ArrayList<>();
    allCosts.add(handleGeneralSingleTrait(trait, bonusPointCostFactor));
    return allCosts.toArray(new FavorableTraitCost[allCosts.size()]);
  }

  private void increaseFavoredDotSum(int favoredDotsSpent) {
    favoredDotSum += favoredDotsSpent;
  }

  private void increaseFavoredPicksSpent() {
    favoredPicksSpent++;
  }

  private void increaseGeneralDotSum(int generalDotsSpent) {
    if (generalDotsSpent == 0) {
      return;
    }
    generalDotSum += generalDotsSpent;
  }

  private Set<Trait> sortTraitsByStatus() {
    Set<Trait> orderedTraits = new LinkedHashSet<>();
    for (Trait trait : traits) {
      if (!trait.getFavorization().isCasteOrFavored()) {
        addAllTraits(orderedTraits, trait);
      }
    }
    for (Trait trait : traits) {
      if (!orderedTraits.contains(trait)) {
        addAllTraits(orderedTraits, trait);
      }
    }
    return orderedTraits;
  }

  private void addAllTraits(final Set<Trait> orderedTraits, Trait trait) {
    orderedTraits.add(trait);
  }
}