package net.sf.anathema.character.main.model.health;

import net.sf.anathema.character.generic.traits.ValuedTraitType;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.model.charm.special.IOxBodyTechniqueConfiguration;

import java.util.ArrayList;
import java.util.List;

public class OxBodyTechniqueArbitratorImpl implements OxBodyTechniqueArbitrator {
  private final List<IOxBodyTechniqueConfiguration> oxBodyList = new ArrayList<>();
  private final Trait[] controllingTraits;

  public OxBodyTechniqueArbitratorImpl(Trait[] toughnessControllingTraits) {
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
    for (ValuedTraitType trait : controllingTraits) {
      maxCount = Math.min(maxCount, trait.getCurrentValue());
    }
    for (IOxBodyTechniqueConfiguration configuration : oxBodyList) {
      oxBodyCount += configuration.getCurrentLearnCount();
    }
    return oxBodyCount + increment <= maxCount;
  }
}