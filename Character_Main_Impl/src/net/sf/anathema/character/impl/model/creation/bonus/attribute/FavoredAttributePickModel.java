package net.sf.anathema.character.impl.model.creation.bonus.attribute;

import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.impl.model.advance.models.AbstractSpendingModel;
import net.sf.anathema.character.library.trait.IFavorableTraitCostCalculator;

public class FavoredAttributePickModel extends AbstractSpendingModel {

  private final IFavorableTraitCostCalculator attributeCalculator;
  private final ICreationPoints creationPoints;

  public FavoredAttributePickModel(IFavorableTraitCostCalculator attributeCalculator, ICreationPoints creationPoints) {
    super("Attributes", "FavoredPick"); //$NON-NLS-1$ //$NON-NLS-2$
    this.attributeCalculator = attributeCalculator;
    this.creationPoints = creationPoints;
  }

  public int getSpentBonusPoints() {
    return 0;
  }

  public Integer getValue() {
    return attributeCalculator.getFavoredPicksSpent();
  }

  public int getAlotment() {
    return creationPoints.getAttributeCreationPoints().getFavorableTraitCount();
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