package net.sf.anathema.character.generic.magic.charms.special;

import java.util.Map;

import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface IOxBodyTechniqueCharm extends ISpecialCharm {

  public abstract ITraitType[] getRelevantTraits();

  public abstract Map<String, HealthLevelType[]> getHealthLevels();
}