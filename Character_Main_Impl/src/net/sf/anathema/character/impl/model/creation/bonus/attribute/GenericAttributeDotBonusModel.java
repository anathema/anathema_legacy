package net.sf.anathema.character.impl.model.creation.bonus.attribute;

import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.points.AttributeGroupPriority;
import net.sf.anathema.character.impl.model.advance.models.AbstractSpendingModel;

public class GenericAttributeDotBonusModel extends AbstractSpendingModel {
  private final AttributeCostCalculator attributeCalculator;
  private final ICreationPoints creationPoints;
  private final boolean isExtraDots;

  public GenericAttributeDotBonusModel(AttributeCostCalculator attributeCalculator,
		  ICreationPoints creationPoints,
		  boolean isExtraDots) {
    super("Attributes", isExtraDots ? "ExtraDots" : "Dots"); //$NON-NLS-1$ //$NON-NLS-2$
    this.attributeCalculator = attributeCalculator;
    this.creationPoints = creationPoints;
    this.isExtraDots = isExtraDots;
  }

  public Integer getValue() {
    return attributeCalculator.getExtraGenericDotsSpent();
  }

  public int getSpentBonusPoints() {
    if (isExtraDots)
    	return 0;
    else
    	return
		  attributeCalculator.getAttributePoints(AttributeGroupPriority.Primary).getBonusPointsSpent() +
		  attributeCalculator.getAttributePoints(AttributeGroupPriority.Secondary).getBonusPointsSpent() +
		  attributeCalculator.getAttributePoints(AttributeGroupPriority.Tertiary).getBonusPointsSpent();
  }

  public int getAlotment() {
    return creationPoints.getAttributeCreationPoints().getExtraGenericDotCount();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}