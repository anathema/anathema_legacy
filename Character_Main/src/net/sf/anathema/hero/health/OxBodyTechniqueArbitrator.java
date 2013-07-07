package net.sf.anathema.hero.health;

import net.sf.anathema.character.main.magic.charms.special.IOxBodyTechniqueConfiguration;

public interface OxBodyTechniqueArbitrator {

  void addOxBodyTechniqueConfiguration(IOxBodyTechniqueConfiguration configuration);

  boolean isIncrementAllowed(int increment);
}