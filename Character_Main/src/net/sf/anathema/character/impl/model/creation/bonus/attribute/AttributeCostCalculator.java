package net.sf.anathema.character.impl.model.creation.bonus.attribute;

import net.sf.anathema.character.generic.template.creation.BonusPointCosts;
import net.sf.anathema.character.generic.template.points.AttributeGroupPriority;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;
import net.sf.anathema.character.generic.template.points.IAttributeGroupPriorityVisitor;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.impl.model.creation.bonus.additional.IAdditionalBonusPointManagment;
import net.sf.anathema.character.impl.model.creation.bonus.basic.ElementCreationCost;
import net.sf.anathema.character.impl.model.creation.bonus.basic.ElementCreationCostCalculator;
import net.sf.anathema.character.impl.model.creation.bonus.basic.ICostElement;
import net.sf.anathema.character.impl.model.creation.bonus.trait.TraitCostElement;
import net.sf.anathema.character.impl.model.creation.bonus.util.TraitGroupCost;
import net.sf.anathema.character.impl.model.creation.bonus.util.TraitSorter;
import net.sf.anathema.character.library.trait.AbstractFavorableTraitCostCalculator;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.TraitGroup;
import net.sf.anathema.character.main.attributes.AttributeModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttributeCostCalculator extends AbstractFavorableTraitCostCalculator implements IAttributeCostCalculator {

  private final Map<Trait, ElementCreationCost> costsByAttribute = new HashMap<>();
  private List<TraitGroupCost> orderedGroups;
  private final TraitGroup[] traitGroups;
  private final BonusPointCosts costs;
  private final List<List<TraitGroup>> priorityPermutations = new ArrayList<>();
  private static final int SORTING_BONUS_COST_SCALE_FACTOR = 1000;

  public AttributeCostCalculator(AttributeModel attributes, IAttributeCreationPoints points, BonusPointCosts costs,
                                 IAdditionalBonusPointManagment additionalPools) {
    super(additionalPools, points, costs.getMaximumFreeAbilityRank(), attributes.getAll());
    this.traitGroups = attributes.getTraitGroups();
    this.costs = costs;
    createPermutations(new ArrayList<TraitGroup>());
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
  public void calculateAttributeCosts() {
    clear();
    costsByAttribute.clear();
    countFavoredTraits();
    IAttributeCreationPoints attributeCreation = (IAttributeCreationPoints) points;
    int bestCost = Integer.MAX_VALUE;
    int bestPermutation = 0;
    for (int i = 0; i != priorityPermutations.size(); i++) {
      List<TraitGroup> permutation = priorityPermutations.get(i);
      int cost = calculateCost(attributeCreation, permutation, false);
      if (cost < bestCost) {
        bestCost = cost;
        bestPermutation = i;
      }
    }
    List<TraitGroup> bestOrder = priorityPermutations.get(bestPermutation);
    orderedGroups = createGroupCost(attributeCreation, bestOrder);
    calculateCost(attributeCreation, bestOrder, true);
  }

  private int calculateCost(IAttributeCreationPoints points, List<TraitGroup> permutation, boolean record) {
    int bonusCost = 0;
    int wastedFreeDots = 0;
    int extraFavoredDotsLeft = this.getExtraFavoredDotCount();
    int extraGenericDotsLeft = this.getExtraGenericDotCount();
    for (int i = 0; i != permutation.size(); i++) {
      TraitGroup group = permutation.get(i);
      int freePointsLeft = points.getCounts()[i];
      boolean favoredInGroup = false;
      Trait[] unsortedTraits = group.getGroupTraits();
      int[] sums = new int[unsortedTraits.length];
      for (int j = 0; j != unsortedTraits.length; j++) {
        sums[j] = costs.getAttributeCosts(unsortedTraits[j]);
      }
      List<Trait> orderedTraits = getSortedTraits(unsortedTraits, sums);
      for (Trait attribute : orderedTraits) {
        favoredInGroup = favoredInGroup || (attribute.isCasteOrFavored() && attribute.getCurrentValue() > attribute.getInitialValue());
        int costFactor = costs.getAttributeCosts(attribute);
        ElementCreationCost cost =
                handleAttribute(attribute, freePointsLeft, favoredInGroup ? extraFavoredDotsLeft : 0, extraGenericDotsLeft, costFactor);
        freePointsLeft -= cost.getDotsSpent();
        extraFavoredDotsLeft -= cost.getExtraFavoredDotsSpent();
        extraGenericDotsLeft -= cost.getExtraGenericDotsSpent();
        bonusCost += cost.getBonusPointsSpent();
        if (record) {
          increaseExtraFavoredDotSum(cost.getExtraFavoredDotsSpent());
          increaseExtraGenericDotSum(cost.getExtraGenericDotsSpent());
          costsByAttribute.put(attribute, cost);
          orderedGroups.get(i).addTraitToCost(attribute, cost);
        }
      }
      wastedFreeDots += freePointsLeft * freePointsLeft;
    }
    return SORTING_BONUS_COST_SCALE_FACTOR * bonusCost + wastedFreeDots;
  }

  private ElementCreationCost handleAttribute(Trait attribute, int freeDots, int extraFavoredDots, int extraGenericDots,
                                              int bonusPointCostFactor) {
    ICostElement element = new TraitCostElement(attribute);
    return new ElementCreationCostCalculator()
            .calculateElementCreationCost(element, freeDots, extraFavoredDots, extraGenericDots, bonusPointCostFactor);
  }

  private List<TraitGroupCost> createGroupCost(IAttributeCreationPoints points, List<TraitGroup> groups) {
    List<TraitGroupCost> priorizedGroups = new ArrayList<>(traitGroups.length);
    for (int groupIndex = 0; groupIndex < groups.size(); groupIndex++) {
      priorizedGroups.add(new TraitGroupCost(groups.get(groupIndex), points.getCounts()[groupIndex]));
    }
    return priorizedGroups;
  }

  private List<Trait> getSortedTraits(Trait[] traits, int[] groupSums) {
    return new TraitSorter().sortDescending(traits, groupSums);
  }

  public TraitGroupCost getAttributePoints(AttributeGroupPriority priority) {
    final TraitGroupCost[] cost = new TraitGroupCost[1];
    priority.accept(new IAttributeGroupPriorityVisitor() {
      @Override
      public void acceptTertiary() {
        cost[0] = orderedGroups.get(2);
      }

      @Override
      public void acceptSecondary() {
        cost[0] = orderedGroups.get(1);
      }

      @Override
      public void acceptPrimary() {
        cost[0] = orderedGroups.get(0);
      }
    });
    return cost[0];
  }

  public TraitGroupCost getAttributePoints(AttributeGroupType attributeGroupType) {
    for (TraitGroupCost cost : orderedGroups) {
      if (cost.getGroup().getGroupId() == attributeGroupType) {
        return cost;
      }
    }
    throw new IllegalArgumentException("Unknown Attribute Group");
  }

  @Override
  public int getBonusPoints() {
    int pointsSpent = 0;
    for (TraitGroupCost cost : orderedGroups) {
      pointsSpent += cost.getBonusPointsSpent();
    }
    return pointsSpent;
  }

  @Override
  protected int getCostFactor(Trait trait) {
    return costs.getAttributeCosts(trait);
  }
}
