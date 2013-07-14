package net.sf.anathema.character.main.magic.model.charm.special;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.health.model.HealthLevelType;

import java.util.LinkedHashMap;
import java.util.Map;

public class OxBodyTechniqueCharm implements IOxBodyTechniqueCharm {

  private final TraitType[] traitTypes;
  private final LinkedHashMap<String, HealthLevelType[]> healthLevels;
  private final String charmId;

  public OxBodyTechniqueCharm(String charmId, TraitType traitType, LinkedHashMap<String, HealthLevelType[]> healthLevels) {
    this(charmId, new TraitType[]{traitType}, healthLevels);
  }

  public OxBodyTechniqueCharm(String charmId, TraitType[] traitTypes, LinkedHashMap<String, HealthLevelType[]> healthLevels) {
    this.traitTypes = traitTypes;
    this.healthLevels = healthLevels;
    this.charmId = charmId;
  }

  @Override
  public TraitType[] getRelevantTraits() {
    return traitTypes;
  }

  @Override
  public Map<String, HealthLevelType[]> getHealthLevels() {
    return new LinkedHashMap<>(healthLevels);
  }

  @Override
  public String getCharmId() {
    return charmId;
  }

  @Override
  public void accept(ISpecialCharmVisitor visitor) {
    visitor.visitOxBodyTechnique(this);
  }
}