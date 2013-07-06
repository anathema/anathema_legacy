package net.sf.anathema.hero.abilities.points;

import net.sf.anathema.character.generic.template.creation.BonusPointCosts;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.main.model.abilities.AbilityModelFetcher;
import net.sf.anathema.character.model.creation.bonus.ability.AbilityCostCalculator;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.points.PointsModel;
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
    ICreationPoints creationPoints = hero.getTemplate().getCreationPoints();
    BonusPointCosts cost = hero.getTemplate().getBonusPointCosts();
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    AbilityCostCalculator abilityCalculator =
            new AbilityCostCalculator(AbilityModelFetcher.fetch(hero), creationPoints.getAbilityCreationPoints(), cost, pointsModel.getAdditionalBonusPoints());
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }
}
