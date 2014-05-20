package net.sf.anathema.hero.attributes.advance.creation;

import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.points.IAttributeCreationPoints;
import net.sf.anathema.character.main.traits.ValuedTraitType;

public class AttributeCreationDataImpl implements AttributeCreationData {

  private IAttributeCreationPoints points;
  private BonusPointCosts costs;

  public AttributeCreationDataImpl(IAttributeCreationPoints points, BonusPointCosts costs) {
    this.points = points;
    this.costs = costs;
  }

  @Override
  public int getAttributeCosts(ValuedTraitType trait) {
    return costs.getAttributeCosts(trait);
  }

  @Override
  public int[] getCounts() {
    return points.getCounts();
  }

  @Override
  public int getExtraFavoredDotCount() {
    return points.getExtraFavoredDotCount();
  }

  @Override
  public int getExtraGenericDotCount() {
    return points.getExtraGenericDotCount();
  }
}
