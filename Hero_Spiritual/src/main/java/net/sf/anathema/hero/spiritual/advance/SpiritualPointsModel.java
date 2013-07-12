package net.sf.anathema.hero.spiritual.advance;

import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.hero.spiritual.advance.creation.VirtueBonusModel;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.hero.points.HeroBonusPointCalculator;
import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.points.overview.SpendingModel;
import net.sf.anathema.hero.points.overview.WeightedCategory;
import net.sf.anathema.hero.spiritual.SpiritualTraitModel;
import net.sf.anathema.hero.spiritual.SpiritualTraitModelFetcher;
import net.sf.anathema.hero.spiritual.advance.creation.SpiritualBonusPointsCalculator;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class SpiritualPointsModel implements HeroModel {

  public static final SimpleIdentifier ID = new SimpleIdentifier("SpiritualPoints");

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
    SpiritualBonusPointsCalculator calculator = createBonusCalculator(hero);
    initBonusCalculation(hero, calculator);
    initBonusOverview(hero, calculator);
  }

  private SpiritualBonusPointsCalculator createBonusCalculator(Hero hero) {
    ICreationPoints creationPoints = hero.getTemplate().getCreationPoints();
    SpiritualTraitModel model = SpiritualTraitModelFetcher.fetch(hero);
    BonusPointCosts costs = hero.getTemplate().getBonusPointCosts();
    return new SpiritualBonusPointsCalculator(model, creationPoints, costs);
  }

  private void initBonusCalculation(Hero hero, HeroBonusPointCalculator calculator) {
    PointModelFetcher.fetch(hero).addBonusPointCalculator(calculator);
  }

  private void initBonusOverview(Hero hero, SpiritualBonusPointsCalculator calculator) {
    PointModelFetcher.fetch(hero).addBonusCategory(new WeightedCategory(500, "Spiritual"));
    ICreationPoints creationPoints = hero.getTemplate().getCreationPoints();
    SpendingModel virtueModel = new VirtueBonusModel(calculator.getVirtueBonusPointCalculator(), creationPoints);
    PointModelFetcher.fetch(hero).addToBonusOverview(virtueModel);
  }
}
