package net.sf.anathema.hero.attributes.advance.creation;

import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.character.main.template.points.AttributeGroupPriority;
import net.sf.anathema.hero.advance.AbstractSpendingModel;

public class AttributeBonusModel extends AbstractSpendingModel {
  private final AttributeBonusPointCalculator attributeCalculator;
  private final AttributeGroupPriority priority;
  private final ICreationPoints creationPoints;

  public AttributeBonusModel(AttributeBonusPointCalculator attributeCalculator, AttributeGroupPriority priority, ICreationPoints creationPoints) {
    super("Attributes", priority.getId());
    this.attributeCalculator = attributeCalculator;
    this.priority = priority;
    this.creationPoints = creationPoints;
  }

  @Override
  public int getSpentBonusPoints() {
    return attributeCalculator.getAttributePoints(priority).getBonusPointsSpent();
  }

  @Override
  public Integer getValue() {
    return attributeCalculator.getAttributePoints(priority).getDotsSpent();
  }

  @Override
  public int getAllotment() {
    return creationPoints.getAttributeCreationPoints().getCount(priority == null ? AttributeGroupPriority.Primary : priority);
  }
}
