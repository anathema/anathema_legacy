package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.model.charm.special.IOxBodyTechniqueConfiguration;
import net.sf.anathema.character.model.health.IOxBodyTechniqueArbitrator;

import java.util.ArrayList;
import java.util.List;

public class OxBodyTechniqueArbitrator implements IOxBodyTechniqueArbitrator {
  private final List<IOxBodyTechniqueConfiguration> oxBodyList = new ArrayList<>();
  private final GenericTrait[] controllingTraits;

  public OxBodyTechniqueArbitrator(GenericTrait[] toughnessControllingTraits) {
    this.controllingTraits = toughnessControllingTraits;
  }

  @Override
  public void addOxBodyTechniqueConfiguration(IOxBodyTechniqueConfiguration oxBodyTechniqueConfiguration) {
    oxBodyList.add(oxBodyTechniqueConfiguration);
  }

  @Override
  public boolean isIncrementAllowed(int increment) {
    int oxBodyCount = 0;
    int maxCount = Integer.MAX_VALUE;
    for (GenericTrait trait : controllingTraits) {
      maxCount = Math.min(maxCount, trait.getCurrentValue());
    }
    for (IOxBodyTechniqueConfiguration configuration : oxBodyList) {
      oxBodyCount += configuration.getCurrentLearnCount();
    }
    return oxBodyCount + increment <= maxCount;
  }
}