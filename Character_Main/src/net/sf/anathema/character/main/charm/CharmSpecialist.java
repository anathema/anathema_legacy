package net.sf.anathema.character.main.charm;

import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.character.main.model.health.HealthModel;
import net.sf.anathema.character.main.model.traits.TraitModel;

public interface CharmSpecialist {

  TraitModel getTraits();

  ExperienceModel getExperience();

  HealthModel getHealth();
}
