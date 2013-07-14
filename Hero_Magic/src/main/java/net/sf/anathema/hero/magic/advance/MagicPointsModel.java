package net.sf.anathema.hero.magic.advance;

import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.hero.advance.CostAnalyzerImpl;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.magic.advance.creation.DefaultMagicModel;
import net.sf.anathema.hero.magic.advance.creation.FavoredMagicModel;
import net.sf.anathema.hero.magic.advance.creation.MagicBonusPointCalculator;
import net.sf.anathema.hero.magic.model.MagicModel;
import net.sf.anathema.hero.magic.model.MagicModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.points.overview.SpendingModel;
import net.sf.anathema.hero.points.overview.WeightedCategory;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class MagicPointsModel implements HeroModel {

  public static final SimpleIdentifier ID = new SimpleIdentifier("MagicPoints");

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    initializeBonusPoints(hero);
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do, until bonus points are created the way, they should be
  }

  private void initializeBonusPoints(Hero hero) {
    MagicBonusPointCalculator calculator = createBonusCalculator(hero);
    initBonusCalculation(hero, calculator);
    initBonusOverview(hero, calculator);
  }

  private void initBonusCalculation(Hero hero, MagicBonusPointCalculator calculator) {
    PointModelFetcher.fetch(hero).addBonusPointCalculator(calculator);
  }

  private void initBonusOverview(Hero hero, MagicBonusPointCalculator calculator) {
    PointModelFetcher.fetch(hero).addBonusCategory(new WeightedCategory(400, "Charms"));
    ICreationPoints creationPoints = hero.getTemplate().getCreationPoints();
    PointModelFetcher.fetch(hero).addToBonusOverview(new DefaultMagicModel(calculator, creationPoints));
    SpendingModel favoredMagicModel = new FavoredMagicModel(calculator, creationPoints);
    if (favoredMagicModel.getAllotment() > 0) {
      PointModelFetcher.fetch(hero).addToBonusOverview(favoredMagicModel);
    }
  }

  private MagicBonusPointCalculator createBonusCalculator(Hero hero) {
    ICreationPoints creationPoints = hero.getTemplate().getCreationPoints();
    MagicModel model = MagicModelFetcher.fetch(hero);
    BonusPointCosts costs = hero.getTemplate().getBonusPointCosts();
    return new MagicBonusPointCalculator(model, creationPoints, costs, new CostAnalyzerImpl(hero));
  }
}
