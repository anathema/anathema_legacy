package net.sf.anathema.character.main.magic.charms.special;

import net.sf.anathema.character.main.health.HealthLevelType;
import net.sf.anathema.character.main.traits.TraitType;

import java.util.Map;

public interface IOxBodyTechniqueCharm extends ISpecialCharm {

  TraitType[] getRelevantTraits();

  Map<String, HealthLevelType[]> getHealthLevels();
}