package net.sf.anathema.hero.attributes.advance.creation;

import net.sf.anathema.hero.template.creation.BonusPointCosts;
import net.sf.anathema.hero.template.points.IAttributeCreationPoints;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.ValuedTraitType;
import net.sf.anathema.hero.attributes.template.AttributePointsTemplate;

public class AttributeCreationDataImpl implements AttributeCreationData {

  private IAttributeCreationPoints points;
  private BonusPointCosts costs;
  private AttributePointsTemplate template;

  public AttributeCreationDataImpl(IAttributeCreationPoints points, BonusPointCosts costs, AttributePointsTemplate template) {
    this.points = points;
    this.costs = costs;
    this.template = template;
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

  @Override
  public int getCalculationBase(TraitType type) {
    return template.standard.calculationBase;
  }
}
