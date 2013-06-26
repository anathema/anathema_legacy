package net.sf.anathema.character.model.creation.bonus.attribute;

import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.library.trait.IFavorableTraitCostCalculator;
import net.sf.anathema.character.model.advance.models.AbstractSpendingModel;

public class FavoredAttributeDotBonusModel extends AbstractSpendingModel {
  private final IFavorableTraitCostCalculator attributeCalculator;
  private final ICreationPoints creationPoints;

  public FavoredAttributeDotBonusModel(IFavorableTraitCostCalculator attributeCalculator, ICreationPoints creationPoints) {
    super("Attributes", "FavoredDot");
    this.attributeCalculator = attributeCalculator;
    this.creationPoints = creationPoints;
  }

  @Override
  public Integer getValue() {
    return attributeCalculator.getExtraFavoredDotsSpent();
  }

  @Override
  public int getSpentBonusPoints() {
    return 0;
  }

  @Override
  public int getAlotment() {
    return creationPoints.getAttributeCreationPoints().getExtraFavoredDotCount();
  }
}