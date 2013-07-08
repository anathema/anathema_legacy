package net.sf.anathema.hero.attributes.points;

import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.points.AttributeGroupPriority;
import net.sf.anathema.character.main.template.points.IAttributeCreationPoints;
import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.attributes.model.AttributesModelFetcher;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.points.overview.ISpendingModel;
import net.sf.anathema.hero.points.overview.WeightedCategory;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class AttributePointsModel implements HeroModel {

  public static final SimpleIdentifier ID = new SimpleIdentifier("AttributePoints");

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(InitializationContext context, Hero hero) {
    initializeBonusPoints(hero);
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do, until bonus points are created the way, they should be
  }

  private void initializeBonusPoints(Hero hero) {
    AttributeCostCalculator calculator = createAttributesBonusCalculator(hero);
    initBonusCalculation(hero, calculator);
    initBonusOverview(hero, calculator);
  }

  private void initBonusCalculation(Hero hero, AttributeCostCalculator calculator) {
    PointModelFetcher.fetch(hero).addBonusPointCalculator(calculator);
  }

  private void initBonusOverview(Hero hero, AttributeCostCalculator calculator) {
    PointModelFetcher.fetch(hero).addBonusCategory(new WeightedCategory(100, "Attributes"));
    PointModelFetcher.fetch(hero).addToBonusOverview(createOverviewModel(calculator, AttributeGroupPriority.Primary, hero.getTemplate()));
    PointModelFetcher.fetch(hero).addToBonusOverview(createOverviewModel(calculator, AttributeGroupPriority.Secondary, hero.getTemplate()));
    PointModelFetcher.fetch(hero).addToBonusOverview(createOverviewModel(calculator, AttributeGroupPriority.Tertiary, hero.getTemplate()));
  }

  private AttributeCostCalculator createAttributesBonusCalculator(Hero hero) {
    IAttributeCreationPoints creationPoints = hero.getTemplate().getCreationPoints().getAttributeCreationPoints();
    AttributeModel attributes = AttributesModelFetcher.fetch(hero);
    BonusPointCosts costs = hero.getTemplate().getBonusPointCosts();
    return new AttributeCostCalculator(attributes, creationPoints, costs);
  }

  public ISpendingModel createOverviewModel(AttributeCostCalculator calculator, AttributeGroupPriority priority, HeroTemplate template) {
    return new AttributeBonusModel(calculator, priority, template.getCreationPoints());
  }
}
