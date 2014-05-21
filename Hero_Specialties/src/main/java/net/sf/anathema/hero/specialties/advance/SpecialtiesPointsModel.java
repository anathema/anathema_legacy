package net.sf.anathema.hero.specialties.advance;

import net.sf.anathema.hero.abilities.model.AbilityModelFetcher;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.points.PointsModel;
import net.sf.anathema.hero.specialties.advance.creation.SpecialtiesBonusPointCalculator;
import net.sf.anathema.hero.specialties.advance.creation.SpecialtyBonusModel;
import net.sf.anathema.hero.specialties.advance.creation.SpecialtyCreationData;
import net.sf.anathema.hero.specialties.template.SpecialtyPointsTemplate;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class SpecialtiesPointsModel implements HeroModel {

  public static final Identifier ID = new SimpleIdentifier("SpecialtiesPoints");
  private SpecialtyPointsTemplate template;

  public SpecialtiesPointsModel(SpecialtyPointsTemplate template) {
    this.template = template;
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    TraitMap traitMap = AbilityModelFetcher.fetch(hero);
    SpecialtyCreationData creationData = new SpecialtyCreationData(template);
    SpecialtiesBonusPointCalculator specialtiesBonusPointCalculator = new SpecialtiesBonusPointCalculator(hero, traitMap, creationData);
    pointsModel.addBonusPointCalculator(specialtiesBonusPointCalculator);
    pointsModel.addToBonusOverview(new SpecialtyBonusModel(specialtiesBonusPointCalculator, template));
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }
}
