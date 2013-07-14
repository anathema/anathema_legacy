package net.sf.anathema.hero.specialties.advance;

import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.hero.abilities.model.AbilityModelFetcher;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.points.PointsModel;
import net.sf.anathema.hero.specialties.advance.creation.SpecialtiesBonusPointCalculator;
import net.sf.anathema.hero.specialties.advance.creation.SpecialtyBonusModel;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class SpecialtiesPointsModel implements HeroModel {

  public static final Identifier ID = new SimpleIdentifier("SpecialtiesPoints");

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    ICreationPoints creationPoints = hero.getTemplate().getCreationPoints();
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    TraitMap traitMap = AbilityModelFetcher.fetch(hero);
    int specialtyCreationPoints = creationPoints.getSpecialtyCreationPoints();
    SpecialtiesBonusPointCalculator specialtiesBonusPointCalculator = new SpecialtiesBonusPointCalculator(hero, traitMap, specialtyCreationPoints);
    pointsModel.addBonusPointCalculator(specialtiesBonusPointCalculator);
    pointsModel.addToBonusOverview(new SpecialtyBonusModel(specialtiesBonusPointCalculator, creationPoints));
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }
}
