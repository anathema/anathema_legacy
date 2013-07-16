package net.sf.anathema.hero.charms.model.special.oxbody;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.magic.charm.special.OxBodyTechniqueSpecials;
import net.sf.anathema.character.main.traits.ValuedTraitType;

import java.util.ArrayList;
import java.util.List;

public class OxBodyTechniqueArbitratorImpl implements OxBodyTechniqueArbitrator {
  private final List<OxBodyTechniqueSpecials> oxBodyList = new ArrayList<>();
  private final Trait[] controllingTraits;

  public OxBodyTechniqueArbitratorImpl(Trait[] toughnessControllingTraits) {
    this.controllingTraits = toughnessControllingTraits;
  }

  @Override
  public void addOxBodyTechniqueConfiguration(OxBodyTechniqueSpecials oxBodyTechniqueSpecials) {
    oxBodyList.add(oxBodyTechniqueSpecials);
  }

  @Override
  public boolean isIncrementAllowed(int increment) {
    int oxBodyCount = 0;
    int maxCount = Integer.MAX_VALUE;
    for (ValuedTraitType trait : controllingTraits) {
      maxCount = Math.min(maxCount, trait.getCurrentValue());
    }
    for (OxBodyTechniqueSpecials configuration : oxBodyList) {
      oxBodyCount += configuration.getCurrentLearnCount();
    }
    return oxBodyCount + increment <= maxCount;
  }
}