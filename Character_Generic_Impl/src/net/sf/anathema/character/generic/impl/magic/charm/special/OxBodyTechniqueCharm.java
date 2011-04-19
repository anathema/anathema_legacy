package net.sf.anathema.character.generic.impl.magic.charm.special;

import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.magic.charms.special.IOxBodyTechniqueCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;
import net.sf.anathema.character.generic.traits.ITraitType;

public class OxBodyTechniqueCharm implements IOxBodyTechniqueCharm {

  private final ITraitType[] traitTypes;
  private final LinkedHashMap<String, HealthLevelType[]> healthLevels;
  private final String charmId;
  
  public OxBodyTechniqueCharm(
	      String charmId,
	      ITraitType traitType,
	      LinkedHashMap<String, HealthLevelType[]> healthLevels) {
	    this (charmId, new ITraitType[] { traitType }, healthLevels);
	  }

  public OxBodyTechniqueCharm(
      String charmId,
      ITraitType[] traitTypes,
      LinkedHashMap<String, HealthLevelType[]> healthLevels) {
    this.traitTypes = traitTypes;
    this.healthLevels = healthLevels;
    this.charmId = charmId;
  }

  public ITraitType[] getRelevantTraits() {
    return traitTypes;
  }

  public Map<String, HealthLevelType[]> getHealthLevels() {
    return new LinkedHashMap<String, HealthLevelType[]>(healthLevels);
  }

  public String getCharmId() {
    return charmId;
  }

  public void accept(ISpecialCharmVisitor visitor) {
    visitor.visitOxBodyTechnique(this);
  }
}