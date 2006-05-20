package net.sf.anathema.character.generic.impl.magic.charm.special;

import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.magic.charms.special.IOxBodyTechniqueCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;
import net.sf.anathema.character.generic.traits.ITraitType;

public class OxBodyTechniqueCharm implements IOxBodyTechniqueCharm {

  private final ITraitType traitType;
  private final LinkedHashMap<String, HealthLevelType[]> healthLevels;
  private final String charmId;

  public OxBodyTechniqueCharm(
      String charmId,
      ITraitType traitType,
      LinkedHashMap<String, HealthLevelType[]> healthLevels) {
    this.traitType = traitType;
    this.healthLevels = healthLevels;
    this.charmId = charmId;
  }

  public ITraitType getRelevantTrait() {
    return traitType;
  }

  public Map<String, HealthLevelType[]> getHealthLevels() {
    return new LinkedHashMap<String, HealthLevelType[]>(healthLevels);
  }

  public String getCharmId() {
    return charmId;
  }

  public void accept(ISpecialCharmVisitor visitor) {
    visitor.acceptOxBodyTechnique(this);
  }
}