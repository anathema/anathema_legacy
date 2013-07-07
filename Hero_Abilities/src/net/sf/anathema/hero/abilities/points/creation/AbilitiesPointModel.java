package net.sf.anathema.hero.abilities.points.creation;

import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.points.IAbilityCreationPoints;
import net.sf.anathema.character.main.model.abilities.AbilityModelFetcher;
import net.sf.anathema.hero.change.ChangeAnnouncer;
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
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    pointsModel.addBonusCategory(new WeightedCategory(200, "Abilities"));
    AbilityCostCalculator abilityCalculator = createCalculator(hero, pointsModel);
    pointsModel.addBonusPointCalculator(abilityCalculator);
    pointsModel.addToBonusOverview(new DefaultAbilityBonusModel(abilityCalculator, hero.getTemplate().getCreationPoints()));
    pointsModel.addToBonusOverview(new FavoredAbilityBonusModel(abilityCalculator, hero.getTemplate().getCreationPoints()));
    pointsModel.addToBonusOverview(new FavoredAbilityPickModel(abilityCalculator, hero.getTemplate().getCreationPoints()));
  }

  private AbilityCostCalculator createCalculator(Hero hero, PointsModel pointsModel) {
    IAbilityCreationPoints abilityCreationPoints = hero.getTemplate().getCreationPoints().getAbilityCreationPoints();
    BonusPointCosts costs = hero.getTemplate().getBonusPointCosts();
    return new AbilityCostCalculator(AbilityModelFetcher.fetch(hero), abilityCreationPoints, costs);
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }
}
