package net.sf.anathema.character.model.health;

import net.sf.anathema.character.model.charm.special.IOxBodyTechniqueConfiguration;

public interface IOxBodyTechniqueArbitrator {

  void addOxBodyTechniqueConfiguration(IOxBodyTechniqueConfiguration configuration);

  boolean isIncrementAllowed(int increment);
}