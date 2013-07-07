package net.sf.anathema.hero.attributes.points;

import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.points.AttributeGroupPriority;
import net.sf.anathema.character.main.template.points.IAttributeCreationPoints;
import net.sf.anathema.character.main.template.points.IAttributeGroupPriorityVisitor;
import net.sf.anathema.character.main.template.points.IFavorableTraitCreationPoints;
import net.sf.anathema.character.main.traits.types.AttributeGroupType;
import net.sf.anathema.character.main.library.trait.FavorableTraitCost;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.TraitGroup;
import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.character.main.creation.bonus.basic.ElementCreationCost;
import net.sf.anathema.character.main.creation.bonus.basic.ElementCreationCostCalculator;
import net.sf.anathema.character.main.creation.bonus.basic.ICostElement;
import net.sf.anathema.character.main.creation.bonus.trait.TraitCostElement;
import net.sf.anathema.character.main.creation.bonus.trait.TraitGroupCost;
import net.sf.anathema.character.main.creation.bonus.trait.TraitSorter;
import net.sf.anathema.hero.points.HeroBonusPointCalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttributeCostCalculator implements HeroBonusPointCalculator {

  private static final int SORTING_BONUS_COST_SCALE_FACTOR = 1000;
  private final Map<Trait, ElementCreationCost> costsByAttribute = new HashMap<>();
  private List<TraitGroupCost> orderedGroups;
  private final TraitGroup[] traitGroups;
  private final BonusPointCosts costs;
  private final List<List<TraitGroup>> priorityPermutations = new ArrayList<>();
  protected final IFavorableTraitCreationPoints points;
  private final Map<Trait, FavorableTraitCost[]> costsByTrait = new HashMap<>();

  public AttributeCostCalculator(AttributeModel attributes, IAttributeCreationPoints points, BonusPointCosts costs) {
    this.points = points;
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
  public void recalculate() {
    clear();
    costsByAttribute.clear();
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

  private void clear() {
    costsByTrait.clear();
  }

  private int getExtraFavoredDotCount() {
    return points.getExtraFavoredDotCount();
  }

  private int getExtraGenericDotCount() {
    return points.getExtraGenericDotCount();
  }
}
