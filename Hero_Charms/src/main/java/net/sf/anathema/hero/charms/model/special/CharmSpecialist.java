package net.sf.anathema.hero.charms.model.special;

import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.health.model.HealthModel;
import net.sf.anathema.hero.traits.TraitModel;

public interface CharmSpecialist {

  TraitModel getTraits();

  ExperienceModel getExperience();

  HealthModel getHealth();
}
