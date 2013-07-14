package net.sf.anathema.hero.attributes.advance;

import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.main.template.points.AttributeGroupPriority;
import net.sf.anathema.character.main.template.points.IAttributeCreationPoints;
import net.sf.anathema.hero.attributes.advance.creation.AttributeBonusModel;
import net.sf.anathema.hero.attributes.advance.creation.AttributeBonusPointCalculator;
import net.sf.anathema.hero.attributes.advance.experience.AttributesExperienceCalculator;
import net.sf.anathema.hero.attributes.advance.experience.AttributesExperienceModel;
import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.attributes.model.AttributesModelFetcher;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.points.PointsModel;
import net.sf.anathema.hero.points.overview.SpendingModel;
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
  public void initialize(HeroEnvironment environment, Hero hero) {
    initializeBonusPoints(hero);
    initializeExperience(hero);
  }
  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }

  private void initializeBonusPoints(Hero hero) {
    AttributeBonusPointCalculator calculator = createAttributesBonusCalculator(hero);
    initBonusCalculation(hero, calculator);
    initBonusOverview(hero, calculator);
  }

  private void initBonusCalculation(Hero hero, AttributeBonusPointCalculator calculator) {
    PointModelFetcher.fetch(hero).addBonusPointCalculator(calculator);
  }

  private void initBonusOverview(Hero hero, AttributeBonusPointCalculator calculator) {
    PointModelFetcher.fetch(hero).addBonusCategory(new WeightedCategory(100, "Attributes"));
    PointModelFetcher.fetch(hero).addToBonusOverview(createOverviewModel(calculator, AttributeGroupPriority.Primary, hero.getTemplate()));
    PointModelFetcher.fetch(hero).addToBonusOverview(createOverviewModel(calculator, AttributeGroupPriority.Secondary, hero.getTemplate()));
    PointModelFetcher.fetch(hero).addToBonusOverview(createOverviewModel(calculator, AttributeGroupPriority.Tertiary, hero.getTemplate()));
  }

  private AttributeBonusPointCalculator createAttributesBonusCalculator(Hero hero) {
    IAttributeCreationPoints creationPoints = hero.getTemplate().getCreationPoints().getAttributeCreationPoints();
    AttributeModel attributes = AttributesModelFetcher.fetch(hero);
    BonusPointCosts costs = hero.getTemplate().getBonusPointCosts();
    return new AttributeBonusPointCalculator(attributes, creationPoints, costs);
  }

  public SpendingModel createOverviewModel(AttributeBonusPointCalculator calculator, AttributeGroupPriority priority, HeroTemplate template) {
    return new AttributeBonusModel(calculator, priority, template.getCreationPoints());
  }

  private void initializeExperience(Hero hero) {
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    AttributeModel model = AttributesModelFetcher.fetch(hero);
    IExperiencePointCosts experienceCost = hero.getTemplate().getExperienceCost();
    AttributesExperienceCalculator calculator = new AttributesExperienceCalculator(experienceCost);
    pointsModel.addToExperienceOverview(new AttributesExperienceModel(model, calculator));
  }
}
