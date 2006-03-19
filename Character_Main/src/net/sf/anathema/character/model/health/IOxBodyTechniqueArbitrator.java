package net.sf.anathema.character.model.health;

import net.sf.anathema.character.model.charm.special.IOxBodyTechniqueConfiguration;

public interface IOxBodyTechniqueArbitrator {

  public void addOxBodyTechniqueConfiguration(IOxBodyTechniqueConfiguration configuration);

  public boolean isIncrementAllowed(int increment);
}