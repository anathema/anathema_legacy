package net.sf.anathema.character.impl.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.model.charm.special.IOxBodyTechniqueConfiguration;
import net.sf.anathema.character.model.health.IOxBodyTechniqueArbitrator;

public class OxBodyTechniqueArbitrator implements IOxBodyTechniqueArbitrator {
  private final List<IOxBodyTechniqueConfiguration> oxBodyList = new ArrayList<IOxBodyTechniqueConfiguration>();
  private final IGenericTrait[] controllingTraits;

  public OxBodyTechniqueArbitrator(IGenericTrait[] toughnessControllingTraits) {
    this.controllingTraits = toughnessControllingTraits;
  }

  public void addOxBodyTechniqueConfiguration(IOxBodyTechniqueConfiguration oxBodyTechniqueConfiguration) {
    oxBodyList.add(oxBodyTechniqueConfiguration);
  }

  public boolean isIncrementAllowed(int increment) {
    int oxBodyCount = 0;
    int maxCount = Integer.MAX_VALUE;
    for (IGenericTrait trait : controllingTraits)
    	maxCount = Math.min(maxCount, trait.getCurrentValue());
    for (IOxBodyTechniqueConfiguration configuration : oxBodyList) {
      oxBodyCount += configuration.getCurrentLearnCount();
    }
    return oxBodyCount + increment <= maxCount;
  }
}