package net.sf.anathema.character.impl.model.charm;

import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.character.main.model.experience.ExperienceModelFetcher;
import net.sf.anathema.character.main.model.health.HealthModel;
import net.sf.anathema.character.main.model.health.HealthModelFetcher;
import net.sf.anathema.character.main.model.traits.TraitModel;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.hero.model.Hero;

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
