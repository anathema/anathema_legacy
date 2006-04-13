package net.sf.anathema.character.impl.model.creation.bonus.attribute;

import net.sf.anathema.character.generic.template.points.AttributeGroupPriority;
import net.sf.anathema.character.presenter.overview.ISpendingModel;

public class AttributeBonusModel implements ISpendingModel {
  private final AttributeCostCalculator attributeCalculator;
  private final AttributeGroupPriority priority;

  public AttributeBonusModel(AttributeCostCalculator attributeCalculator, AttributeGroupPriority priority) {
    this.attributeCalculator = attributeCalculator;
    this.priority = priority;
  }

  public int getSpentBonusPoints() {
    return attributeCalculator.getAttributePoints(priority).getBonusPointsSpent();
  }

  public Integer getValue() {
    return attributeCalculator.getAttributePoints(priority).getDotsSpent();
  }

  public String getId() {
    return "Attributes"; //$NON-NLS-1$
  }
}