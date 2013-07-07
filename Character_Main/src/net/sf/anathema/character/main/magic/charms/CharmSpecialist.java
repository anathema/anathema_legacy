package net.sf.anathema.character.main.magic.charms;

import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.health.HealthModel;
import net.sf.anathema.hero.traits.TraitModel;

public interface CharmSpecialist {

  TraitModel getTraits();

  ExperienceModel getExperience();

  HealthModel getHealth();
}
