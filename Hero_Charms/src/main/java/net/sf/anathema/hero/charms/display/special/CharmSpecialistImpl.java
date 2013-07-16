package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.character.main.magic.charm.CharmSpecialist;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.health.HealthModel;
import net.sf.anathema.hero.health.HealthModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.TraitModel;
import net.sf.anathema.hero.traits.TraitModelFetcher;

public class CharmSpecialistImpl implements CharmSpecialist {

  private Hero hero;

  public CharmSpecialistImpl(Hero hero) {
    this.hero = hero;
  }

  @Override
  public TraitModel getTraits() {
    return TraitModelFetcher.fetch(hero);
  }

  @Override
  public ExperienceModel getExperience() {
    return ExperienceModelFetcher.fetch(hero);
  }

  @Override
  public HealthModel getHealth() {
    return HealthModelFetcher.fetch(hero);
  }
}
