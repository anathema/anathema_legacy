package net.sf.anathema.character.impl.model.creation.bonus.attribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.template.creation.IBonusPointCosts;
import net.sf.anathema.character.generic.template.points.AttributeGroupPriority;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;
import net.sf.anathema.character.generic.template.points.IAttributeGroupPriorityVisitor;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.impl.model.creation.bonus.basic.ElementCreationCost;
import net.sf.anathema.character.impl.model.creation.bonus.basic.ElementCreationCostCalculator;
import net.sf.anathema.character.impl.model.creation.bonus.basic.ICostElement;
import net.sf.anathema.character.impl.model.creation.bonus.trait.TraitCostElement;
import net.sf.anathema.character.impl.model.creation.bonus.util.TraitGroupCost;
import net.sf.anathema.character.impl.model.creation.bonus.util.TraitGroupSorter;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.TraitGroup;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class AttributeCostCalculator implements IAttributeCostCalculator {

  private final Map<ITrait, ElementCreationCost> costsByAttribute = new HashMap<ITrait, ElementCreationCost>();
  private List<TraitGroupCost> orderedGroups;
  private final TraitGroup[] traitGroups;
  private final IAttributeCreationPoints attributePoints;
  private final IBonusPointCosts costs;

  public AttributeCostCalculator(
      ICoreTraitConfiguration traitConfiguration,
      IAttributeCreationPoints attributePoints,
      IBonusPointCosts costs) {
    this.traitGroups = createTraitGroups(traitConfiguration);
    this.attributePoints = attributePoints;
    this.costs = costs;
  }

  private TraitGroup[] createTraitGroups(ICoreTraitConfiguration traitConfiguration) {
    IIdentifiedTraitTypeGroup[] attributeTypeGroups = traitConfiguration.getAttributeTypeGroups();
    TraitGroup[] newGroups = new TraitGroup[attributeTypeGroups.length];
    for (int index = 0; index < newGroups.length; index++) {
      newGroups[index] = new TraitGroup(traitConfiguration, attributeTypeGroups[index]);
    }
    return newGroups;
  }

  public void calculateAttributeCosts() {
    clear();
    orderedGroups = calculateGroupPriorites(attributePoints);
    for (TraitGroupCost group : orderedGroups) {
      int freePointsLeft = group.getPointsToSpend();
      for (IFavorableTrait attribute : group.getTraits()) {
        int costFactor = costs.getAttributeCosts(attribute);
        ElementCreationCost cost = handleAttribute((IDefaultTrait) attribute, freePointsLeft, costFactor);
        freePointsLeft -= cost.getDotsSpent();
        costsByAttribute.put(attribute, cost);
        group.addTraitToCost(attribute, cost);
      }
    }
  }

  private void clear() {
    costsByAttribute.clear();
  }

  private ElementCreationCost handleAttribute(IDefaultTrait attribute, int freeDots, int bonusPointCostFactor) {
    ICostElement element = new TraitCostElement(attribute);
    return new ElementCreationCostCalculator().calculateElementCreationCost(element, freeDots, bonusPointCostFactor);
  }

  private List<TraitGroupCost> calculateGroupPriorites(IAttributeCreationPoints points) {
    int[] groupSums = getGroupCosts();
    List<TraitGroup> sortedGroups = getSortedGroups(groupSums);
    List<TraitGroupCost> priorizedGroups = new ArrayList<TraitGroupCost>(traitGroups.length);
    for (int groupIndex = 0; groupIndex < sortedGroups.size(); groupIndex++) {
      priorizedGroups.add(new TraitGroupCost(sortedGroups.get(groupIndex), points.getCounts()[groupIndex]));
    }
    return priorizedGroups;
  }

  private List<TraitGroup> getSortedGroups(int[] groupSums) {
    return new TraitGroupSorter().sortDescending(traitGroups, groupSums);
  }

  private int[] getGroupCosts() {
    int[] groupSums = new int[traitGroups.length];
    for (int i = 0; i < groupSums.length; i++) {
      TraitGroup attributeGroup = traitGroups[i];
      int startValues = attributeGroup.getInitialSum();
      groupSums[i] = attributeGroup.getCreationValueSum() - startValues;
    }
    return groupSums;
  }

  public TraitGroupCost getAttributePoints(AttributeGroupPriority priority) {
    final TraitGroupCost[] cost = new TraitGroupCost[1];
    priority.accept(new IAttributeGroupPriorityVisitor() {
      public void acceptTertiary() {
        cost[0] = orderedGroups.get(2);
      }

      public void acceptSecondary() {
        cost[0] = orderedGroups.get(1);
      }

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
    throw new IllegalArgumentException("Unknown Attribute Group"); //$NON-NLS-1$
  }

  public int getBonusPoints() {
    int pointsSpent = 0;
    for (TraitGroupCost cost : orderedGroups) {
      pointsSpent += cost.getBonusPointsSpent();
    }
    return pointsSpent;
  }

  public ElementCreationCost getCosts(IDefaultTrait attribute) {
    return costsByAttribute.get(attribute);
  }
}