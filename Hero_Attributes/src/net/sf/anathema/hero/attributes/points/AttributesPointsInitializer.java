package net.sf.anathema.hero.attributes.points;

import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.generic.template.creation.BonusPointCosts;
import net.sf.anathema.character.generic.template.points.AttributeGroupPriority;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;
import net.sf.anathema.character.main.model.attributes.AttributeModel;
import net.sf.anathema.hero.points.PointsModel;
import net.sf.anathema.hero.points.overview.ISpendingModel;
import net.sf.anathema.hero.points.overview.WeightedCategory;

public class AttributesPointsInitializer {

  private AttributeCostCalculator attributeCalculator;

  public void initialize(AttributeModel attributes, PointsModel pointsModel, HeroTemplate template) {
    BonusPointCosts costs = template.getBonusPointCosts();
    IAttributeCreationPoints creationPoints = template.getCreationPoints().getAttributeCreationPoints();
    this.attributeCalculator = new AttributeCostCalculator(attributes, creationPoints, costs);
    pointsModel.addBonusPointCalculator(attributeCalculator);
    pointsModel.addBonusCategory(new WeightedCategory(100, "Attributes"));
    pointsModel.addToBonusOverview(createOverviewModel(AttributeGroupPriority.Primary, template));
    pointsModel.addToBonusOverview(createOverviewModel(AttributeGroupPriority.Secondary, template));
    pointsModel.addToBonusOverview(createOverviewModel(AttributeGroupPriority.Tertiary, template));
  }

  public ISpendingModel createOverviewModel(AttributeGroupPriority priority, HeroTemplate template) {
    return new AttributeBonusModel(attributeCalculator, priority, template.getCreationPoints());
  }
}
