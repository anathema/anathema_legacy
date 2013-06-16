package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.traits.TraitType;

import java.util.Map;

public interface IOxBodyTechniqueCharm extends ISpecialCharm {

  TraitType[] getRelevantTraits();

  Map<String, HealthLevelType[]> getHealthLevels();
}