package net.sf.anathema.character.impl.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.model.charm.special.IOxBodyTechniqueConfiguration;
import net.sf.anathema.character.model.health.IOxBodyTechniqueArbitrator;

public class OxBodyTechniqueArbitrator implements IOxBodyTechniqueArbitrator {
  private final List<IOxBodyTechniqueConfiguration> oxBodyList = new ArrayList<IOxBodyTechniqueConfiguration>();
  private final IGenericTrait controllingTrait;

  public OxBodyTechniqueArbitrator(IGenericTrait toughnessControllingTrait) {
    this.controllingTrait = toughnessControllingTrait;
  }

  public void addOxBodyTechniqueConfiguration(IOxBodyTechniqueConfiguration oxBodyTechniqueConfiguration) {
    oxBodyList.add(oxBodyTechniqueConfiguration);
  }

  public boolean isIncrementAllowed(int increment) {
    int oxBodyCount = 0;
    for (IOxBodyTechniqueConfiguration configuration : oxBodyList) {
      oxBodyCount += configuration.getCurrentLearnCount();
    }
    return oxBodyCount + increment <= controllingTrait.getCurrentValue();
  }
}