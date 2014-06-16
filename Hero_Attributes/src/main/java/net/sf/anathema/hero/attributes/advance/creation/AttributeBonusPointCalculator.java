package net.sf.anathema.hero.attributes.advance.creation;

import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.points.HeroBonusPointCalculator;
import net.sf.anathema.hero.template.points.AttributeGroupPriority;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitGroup;

import java.util.ArrayList;
import java.util.List;

public class AttributeBonusPointCalculator implements HeroBonusPointCalculator {

  private static final int SORTING_BONUS_COST_SCALE_FACTOR = 1000;
  private List<TraitGroupCost> orderedGroups;
  private final TraitGroup[] traitGroups;
  private final List<List<TraitGroup>> priorityPermutations = new ArrayList<>();
  private final AttributeCreationData creationData;

  public AttributeBonusPointCalculator(AttributeModel attributes, AttributeCreationData creationData) {
    this.creationData = creationData;
    this.traitGroups = attributes.getTraitGroups();
    createPermutations(new ArrayList<>());
  }

  private void createPermutations(List<TraitGroup> parent) {
    boolean isLeaf = true;
    for (TraitGroup entry : traitGroups) {
      if (parent.contains(entry)) {
        continue;
      }
      isLeaf = false;
      List<TraitGroup> child = new ArrayList<>(parent);
      child.add(entry);
      createPermutations(child);
    }
    if (isLeaf) {
      priorityPermutations.add(parent);
    }
  }

  @Override
  public void recalculate() {
    int bestCost = Integer.MAX_VALUE;
    int bestPermutation = 0;
    for (int i = 0; i != priorityPermutations.size(); i++) {
      List<TraitGroup> permutation = priorityPermutations.get(i);
      int cost = calculateCost(permutation, false);
      if (cost < bestCost) {
        bestCost = cost;
        bestPermutation = i;
      }
    }
    List<TraitGroup> bestOrder = priorityPermutations.get(bestPermutation);
    orderedGroups = createGroupCost(bestOrder);
    calculateCost(bestOrder, true);
  }

  private int calculateCost(List<TraitGroup> permutation, boolean record) {
    int bonusCost = 0;
    int wastedFreeDots = 0;
    int extraFavoredDotsLeft = this.getExtraFavoredDotCount();
    int extraGenericDotsLeft = this.getExtraGenericDotCount();
    for (int i = 0; i != permutation.size(); i++) {
      TraitGroup group = permutation.get(i);
      int freePointsLeft = creationData.getCounts()[i];
      boolean favoredInGroup = false;
      Trait[] unsortedTraits = group.getGroupTraits();
      int[] sums = new int[unsortedTraits.length];
      for (int j = 0; j != unsortedTraits.length; j++) {
        sums[j] = creationData.getAttributeCosts(unsortedTraits[j]);
      }
      List<Trait> orderedTraits = getSortedTraits(unsortedTraits, sums);
      for (Trait attribute : orderedTraits) {
        favoredInGroup = favoredInGroup || (attribute.isCasteOrFavored() && attribute.getCurrentValue() > attribute.getInitialValue());
        int costFactor = creationData.getAttributeCosts(attribute);
        ElementCreationCost cost = handleAttribute(attribute, freePointsLeft, favoredInGroup ? extraFavoredDotsLeft : 0,
                extraGenericDotsLeft, costFactor);
        freePointsLeft -= cost.getDotsSpent();
        extraFavoredDotsLeft -= cost.getExtraFavoredDotsSpent();
        extraGenericDotsLeft -= cost.getExtraGenericDotsSpent();
        bonusCost += cost.getBonusPointsSpent();
        if (record) {
          orderedGroups.get(i).addTraitToCost(attribute, cost);
        }
      }
      wastedFreeDots += freePointsLeft * freePointsLeft;
    }
    return SORTING_BONUS_COST_SCALE_FACTOR * bonusCost + wastedFreeDots;
  }

  private ElementCreationCost handleAttribute(Trait attribute, int freeDots, int extraFavoredDots, int extraGenericDots,
                                              int bonusPointCostFactor) {
    CostElement element = new TraitCostElement(attribute, creationData);
    return new ElementCreationCostCalculator().calculateElementCreationCost(element, freeDots, extraFavoredDots,
            extraGenericDots, bonusPointCostFactor);
  }

  private List<TraitGroupCost> createGroupCost(List<TraitGroup> groups) {
    List<TraitGroupCost> priorizedGroups = new ArrayList<>(traitGroups.length);
    for (TraitGroup group : groups) {
      priorizedGroups.add(new TraitGroupCost(group));
    }
    return priorizedGroups;
  }

  private List<Trait> getSortedTraits(Trait[] traits, int[] groupSums) {
    return new TraitSorter().sortDescending(traits, groupSums);
  }

  public TraitGroupCost getAttributePoints(AttributeGroupPriority priority) {
    switch (priority) {
      case Primary:
        return orderedGroups.get(0);
      case Secondary:
        return orderedGroups.get(1);
      case Tertiary:
        return orderedGroups.get(2);
      default:
        throw new IllegalArgumentException("Unknown Attribute Group Priority: " + priority);
    }
  }

  @Override
  public int getBonusPointCost() {
    int pointsSpent = 0;
    for (TraitGroupCost cost : orderedGroups) {
      pointsSpent += cost.getBonusPointsSpent();
    }
    return pointsSpent;
  }

  @Override
  public int getBonusPointsGranted() {
    return 0;
  }

  private int getExtraFavoredDotCount() {
    return creationData.getExtraFavoredDotCount();
  }

  private int getExtraGenericDotCount() {
    return creationData.getExtraGenericDotCount();
  }
}