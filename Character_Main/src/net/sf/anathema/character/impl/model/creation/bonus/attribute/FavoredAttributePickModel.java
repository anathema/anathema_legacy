package net.sf.anathema.character.impl.model.creation.bonus.attribute;

import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.impl.model.advance.models.AbstractSpendingModel;
import net.sf.anathema.character.library.trait.IFavorableTraitCostCalculator;

public class FavoredAttributePickModel extends AbstractSpendingModel {

  private final IFavorableTraitCostCalculator attributeCalculator;
  private final ICreationPoints creationPoints;

  public FavoredAttributePickModel(IFavorableTraitCostCalculator attributeCalculator, ICreationPoints creationPoints) {
    super("Attributes", "FavoredPick");
    this.attributeCalculator = attributeCalculator;
    this.creationPoints = creationPoints;
  }

  @Override
  public int getSpentBonusPoints() {
    return 0;
  }

  @Override
  public Integer getValue() {
    return attributeCalculator.getFavoredPicksSpent();
  }

  @Override
  public int getAlotment() {
    return creationPoints.getAttributeCreationPoints().getFavorableTraitCount();
  }
}