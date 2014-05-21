package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.hero.charms.model.special.CharmSpecialist;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.health.model.HealthModel;
import net.sf.anathema.hero.health.model.HealthModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.model.TraitModel;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;

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
