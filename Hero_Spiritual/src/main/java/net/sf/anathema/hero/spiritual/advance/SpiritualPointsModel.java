package net.sf.anathema.hero.spiritual.advance;

import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.points.PointsModel;
import net.sf.anathema.hero.spiritual.SpiritualTraitModel;
import net.sf.anathema.hero.spiritual.SpiritualTraitModelFetcher;
import net.sf.anathema.hero.spiritual.advance.creation.DefaultSpiritualCreationData;
import net.sf.anathema.hero.spiritual.advance.creation.SpiritualBonusPointsCalculator;
import net.sf.anathema.hero.spiritual.advance.experience.EssenceExperienceModel;
import net.sf.anathema.hero.spiritual.advance.experience.SpiritualExperienceCalculator;
import net.sf.anathema.hero.spiritual.advance.experience.WillpowerExperienceModel;
import net.sf.anathema.hero.spiritual.template.SpiritualPointsTemplate;
import net.sf.anathema.hero.template.creation.BonusPointCosts;
import net.sf.anathema.hero.template.experience.IExperiencePointCosts;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class SpiritualPointsModel implements HeroModel {

  public static final SimpleIdentifier ID = new SimpleIdentifier("SpiritualPoints");
  private SpiritualPointsTemplate template;

  public SpiritualPointsModel(SpiritualPointsTemplate template) {
    this.template = template;
  }

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
    // nothing to do, until bonus points are created the way, they should be
  }

  private void initializeBonusPoints(Hero hero) {
    SpiritualBonusPointsCalculator calculator = createBonusCalculator(hero);
    PointModelFetcher.fetch(hero).addBonusPointCalculator(calculator);
  }

  private SpiritualBonusPointsCalculator createBonusCalculator(Hero hero) {
    SpiritualTraitModel model = SpiritualTraitModelFetcher.fetch(hero);
    BonusPointCosts costs = hero.getTemplate().getBonusPointCosts();
    DefaultSpiritualCreationData creationData = new DefaultSpiritualCreationData(template, costs);
    return new SpiritualBonusPointsCalculator(model, creationData);
  }

  private void initializeExperience(Hero hero) {
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    SpiritualTraitModel model = SpiritualTraitModelFetcher.fetch(hero);
    IExperiencePointCosts experienceCost = hero.getTemplate().getExperienceCost();
    SpiritualExperienceCalculator calculator = new SpiritualExperienceCalculator(experienceCost);
    pointsModel.addToExperienceOverview(new EssenceExperienceModel(model, calculator));
    pointsModel.addToExperienceOverview(new WillpowerExperienceModel(model, calculator));
  }
}
