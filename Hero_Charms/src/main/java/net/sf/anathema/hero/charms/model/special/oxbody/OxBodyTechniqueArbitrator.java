package net.sf.anathema.hero.charms.model.special.oxbody;

public interface OxBodyTechniqueArbitrator {

  void addOxBodyTechniqueConfiguration(OxBodyTechniqueSpecials configuration);

  boolean isIncrementAllowed(int increment);
}