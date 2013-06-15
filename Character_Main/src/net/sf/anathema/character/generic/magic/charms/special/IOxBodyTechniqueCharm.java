package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.traits.ITraitType;

import java.util.Map;

public interface IOxBodyTechniqueCharm extends ISpecialCharm {

  ITraitType[] getRelevantTraits();

  Map<String, HealthLevelType[]> getHealthLevels();
}