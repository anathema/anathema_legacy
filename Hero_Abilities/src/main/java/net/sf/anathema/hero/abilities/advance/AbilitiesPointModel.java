package net.sf.anathema.hero.abilities.advance;

import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.character.main.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.main.template.points.IAbilityCreationPoints;
import net.sf.anathema.hero.abilities.advance.creation.AbilityCostCalculatorImpl;
import net.sf.anathema.hero.abilities.advance.creation.DefaultAbilityBonusModel;
import net.sf.anathema.hero.abilities.advance.creation.FavoredAbilityBonusModel;
import net.sf.anathema.hero.abilities.advance.creation.FavoredAbilityPickModel;
import net.sf.anathema.hero.abilities.advance.experience.AbilityExperienceCalculator;
import net.sf.anathema.hero.abilities.advance.experience.AbilityExperienceModel;
import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.abilities.model.AbilityModelFetcher;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.points.PointsModel;
import net.sf.anathema.hero.points.overview.WeightedCategory;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class AbilitiesPointModel implements HeroModel {

  public static final Identifier ID = new SimpleIdentifier("AbilitiesPoints");

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(InitializationContext context, Hero hero) {
    initializeBonusPoints(hero);
    initializeExperiencePoints(hero);
  }

  private void initializeBonusPoints(Hero hero) {
    AbilityCostCalculatorImpl abilityCalculator = createCalculator(hero);
    initializeBonusCalculator(hero, abilityCalculator);
    initializeBonusOverview(hero, abilityCalculator);
  }

  private void initializeBonusCalculator(Hero hero, AbilityCostCalculatorImpl abilityCalculator) {
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    pointsModel.addBonusPointCalculator(abilityCalculator);
  }

  private void initializeBonusOverview(Hero hero, AbilityCostCalculatorImpl abilityCalculator) {
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    pointsModel.addBonusCategory(new WeightedCategory(200, "Abilities"));
    ICreationPoints creationPoints = hero.getTemplate().getCreationPoints();
    pointsModel.addToBonusOverview(new DefaultAbilityBonusModel(abilityCalculator, creationPoints));
    pointsModel.addToBonusOverview(new FavoredAbilityBonusModel(abilityCalculator, creationPoints));
    pointsModel.addToBonusOverview(new FavoredAbilityPickModel(abilityCalculator, creationPoints));
  }

  private void initializeExperiencePoints(Hero hero) {
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    AbilitiesModel abilities = AbilityModelFetcher.fetch(hero);
    IExperiencePointCosts experienceCost = hero.getTemplate().getExperienceCost();
    AbilityExperienceCalculator calculator = new AbilityExperienceCalculator(experienceCost);
    pointsModel.addToExperienceOverview(new AbilityExperienceModel(abilities, calculator));
  }

  private AbilityCostCalculatorImpl createCalculator(Hero hero) {
    IAbilityCreationPoints abilityCreationPoints = hero.getTemplate().getCreationPoints().getAbilityCreationPoints();
    BonusPointCosts costs = hero.getTemplate().getBonusPointCosts();
    return new AbilityCostCalculatorImpl(AbilityModelFetcher.fetch(hero), abilityCreationPoints, costs);
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }
}
