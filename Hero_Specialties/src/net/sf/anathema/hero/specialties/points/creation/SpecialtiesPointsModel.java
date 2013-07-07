package net.sf.anathema.hero.specialties.points.creation;

import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.hero.abilities.model.AbilityModelFetcher;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.points.PointsModel;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class SpecialtiesPointsModel implements HeroModel {

  public static final Identifier ID = new SimpleIdentifier("SpecialtiesPoints");

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(InitializationContext context, Hero hero) {
    ICreationPoints creationPoints = hero.getTemplate().getCreationPoints();
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    TraitMap traitMap = AbilityModelFetcher.fetch(hero);
    int specialtyCreationPoints = creationPoints.getSpecialtyCreationPoints();
    SpecialtiesCostCalculator specialtiesCostCalculator = new SpecialtiesCostCalculator(hero, traitMap, specialtyCreationPoints);
    pointsModel.addBonusPointCalculator(specialtiesCostCalculator);
    pointsModel.addToBonusOverview(new SpecialtyBonusModel(specialtiesCostCalculator, creationPoints));
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }
}
