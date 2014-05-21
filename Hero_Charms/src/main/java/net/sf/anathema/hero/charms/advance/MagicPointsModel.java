package net.sf.anathema.hero.charms.advance;

import net.sf.anathema.hero.template.creation.ICreationPoints;
import net.sf.anathema.hero.charms.advance.costs.CostAnalyzerImpl;
import net.sf.anathema.hero.charms.advance.creation.DefaultMagicModel;
import net.sf.anathema.hero.charms.advance.creation.FavoredMagicModel;
import net.sf.anathema.hero.charms.advance.creation.MagicCreationCostCalculator;
import net.sf.anathema.hero.charms.advance.experience.CharmExperienceCostCalculator;
import net.sf.anathema.hero.charms.advance.experience.CharmExperienceModel;
import net.sf.anathema.hero.charms.advance.experience.MagicExperienceCosts;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.charms.template.advance.MagicPointsTemplate;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.character.magic.charm.martial.MartialArtsLevel;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.points.PointsModel;
import net.sf.anathema.hero.points.overview.SpendingModel;
import net.sf.anathema.hero.points.overview.WeightedCategory;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class MagicPointsModel implements HeroModel {

  public static final SimpleIdentifier ID = new SimpleIdentifier("MagicPoints");
  private MagicPointsTemplate template;
  private MartialArtsLevel standardMartialArtsLevel;

  public MagicPointsModel(MagicPointsTemplate template) {
    this.template = template;
  }

  public MagicPointsTemplate getTemplate() {
    return template;
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    this.standardMartialArtsLevel = CharmsModelFetcher.fetch(hero).getStandardMartialArtsLevel();
    initializeBonusPoints(hero);
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do, until bonus points are created the way, they should be
  }

  public MagicExperienceCosts getExperienceCost() {
    return new MagicExperienceCosts(template, standardMartialArtsLevel);
  }

  private void initializeBonusPoints(Hero hero) {
    initCreation(hero);
    initializeExperience(hero);
  }

  private void initializeExperience(Hero hero) {
    CharmExperienceCostCalculator calculator = createExperienceCalculator();
    initExperienceOverview(hero, calculator);
  }

  private void initCreation(Hero hero) {
    MagicCreationCostCalculator calculator = createBonusCalculator(hero);
    initBonusCalculation(hero, calculator);
    initBonusOverview(hero, calculator);
  }

  private void initBonusCalculation(Hero hero, MagicCreationCostCalculator calculator) {
    PointModelFetcher.fetch(hero).addBonusPointCalculator(calculator);
  }

  private void initBonusOverview(Hero hero, MagicCreationCostCalculator calculator) {
    PointModelFetcher.fetch(hero).addBonusCategory(new WeightedCategory(400, "Charms"));
    ICreationPoints creationPoints = hero.getTemplate().getCreationPoints();
    PointModelFetcher.fetch(hero).addToBonusOverview(new DefaultMagicModel(calculator, creationPoints));
    SpendingModel favoredMagicModel = new FavoredMagicModel(calculator, creationPoints);
    if (favoredMagicModel.getAllotment() > 0) {
      PointModelFetcher.fetch(hero).addToBonusOverview(favoredMagicModel);
    }
  }

  private MagicCreationCostCalculator createBonusCalculator(Hero hero) {
    CharmsModel model = CharmsModelFetcher.fetch(hero);
    return new MagicCreationCostCalculator(model.getMagicCostEvaluator(), template, standardMartialArtsLevel, new CostAnalyzerImpl(hero));
  }

  private CharmExperienceCostCalculator createExperienceCalculator() {
    return new CharmExperienceCostCalculator(getExperienceCost());
  }

  private void initExperienceOverview(Hero hero, CharmExperienceCostCalculator calculator) {
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    pointsModel.addToExperienceOverview(new CharmExperienceModel(calculator, hero));
  }
}
