package net.sf.anathema.character.impl.model.creation.bonus.attribute;

import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.points.AttributeGroupPriority;
import net.sf.anathema.character.impl.model.advance.models.AbstractSpendingModel;

public class GenericAttributeDotBonusModel extends AbstractSpendingModel {
  private final AttributeCostCalculator attributeCalculator;
  private final ICreationPoints creationPoints;
  private final boolean isExtraDots;

  public GenericAttributeDotBonusModel(AttributeCostCalculator attributeCalculator, ICreationPoints creationPoints, boolean isExtraDots) {
    super("Attributes", isExtraDots ? "ExtraDots" : "Dots");
    this.attributeCalculator = attributeCalculator;
    this.creationPoints = creationPoints;
    this.isExtraDots = isExtraDots;
  }

  @Override
  public Integer getValue() {
    return attributeCalculator.getExtraGenericDotsSpent();
  }

  @Override
  public int getSpentBonusPoints() {
    if (isExtraDots) {
      return 0;
    } else {
      return attributeCalculator.getAttributePoints(AttributeGroupPriority.Primary).getBonusPointsSpent() +
             attributeCalculator.getAttributePoints(AttributeGroupPriority.Secondary).getBonusPointsSpent() +
             attributeCalculator.getAttributePoints(AttributeGroupPriority.Tertiary).getBonusPointsSpent();
    }
  }

  @Override
  public int getAlotment() {
    return creationPoints.getAttributeCreationPoints().getExtraGenericDotCount();
  }
}