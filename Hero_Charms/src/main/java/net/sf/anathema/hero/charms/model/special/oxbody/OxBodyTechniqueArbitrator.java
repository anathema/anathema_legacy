package net.sf.anathema.hero.charms.model.special.oxbody;

import net.sf.anathema.character.main.magic.model.charm.special.OxBodyTechniqueSpecials;

public interface OxBodyTechniqueArbitrator {

  void addOxBodyTechniqueConfiguration(OxBodyTechniqueSpecials configuration);

  boolean isIncrementAllowed(int increment);
}